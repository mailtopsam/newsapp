package com.stackroute.favourite.Controller;

import com.stackroute.favourite.Exception.CountException;
import com.stackroute.favourite.model.UserLikes;
import com.stackroute.favourite.repository.LikeRepository;
import com.stackroute.favourite.service.LikeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//annotation for logging
@Slf4j
//annotation for parameterised constructor using lombok dependency
@AllArgsConstructor
//annotation is used to create Restful web services using Spring MVC
@RestController
//annotation used to map HTTP requests to handler methods
@RequestMapping("api/v1/")
public class Controller {

    @Autowired
    private LikeService likeService;
    @Autowired
    private LikeRepository likeRepository;


    //to get the favourite articles of a user
    @GetMapping("/favourites")
    public List<UserLikes> getAllLikesOfUser(@RequestParam (value = "email")String email){
        log.info("Calling getAllLikes service");
        return likeService.getAllLikes(email);

    }
    /**
     * to add an article to favourite list of a user
     **/
    @PostMapping("/favourites/like/{email}/{id}")
    public String addAddLikeToUser(@PathVariable String email,@PathVariable int id) throws CountException {
       UserLikes userLikes = likeRepository.findByEmailAndFavourite_index(email,id);
       if (userLikes == null) {
           RestTemplate restTemplate = new RestTemplate();
           String url = "http://localhost:8086/news/like/" + id;


               UserLikes userLikes1 = restTemplate.getForObject(url, UserLikes.class);
               userLikes1.setEmailId(email);
               log.info("addLike service called to add the article to Favourites of "+ email);
               return likeService.addLike(userLikes1);


       }
       else {
           log.error("Article is already liked by "+ email);
           return "Already Liked the News Article";
       }

    }
    /**
    delete article from favourite list of a user
    **/
    @PostMapping("/favourites/dislike/{email}/{id}")
    public String DislikeArticle(@PathVariable String email,@PathVariable int id){

        UserLikes userLikes = likeRepository.findByEmailAndFavourite_index(email,id);
        if (userLikes != null) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8086/news/dislike/" + id;
            UserLikes userLikes1 = restTemplate.getForObject(url, UserLikes.class);
            userLikes1.setEmailId(email);
            log.info("deleteLike service called to remove the article from Favourites of "+ email);
            return likeService.deleteLike(userLikes1);
        }
        else {
            log.error("Article is not available in Favourites of "+ email +", hence cannot Dislike ");
            return "Nothing to Dislike";
        }
    }

}
