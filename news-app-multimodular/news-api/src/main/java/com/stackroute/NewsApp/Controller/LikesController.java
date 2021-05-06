package com.stackroute.NewsApp.Controller;

import com.stackroute.NewsApp.Service.NewsService;
import com.stackroute.NewsApp.model.Article;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//annotation  used to create Restful web services using Spring MVC
@RestController
//annotation for parameterised constructor
@AllArgsConstructor
class NewsLikeController {

    @Autowired
    private NewsService service;

    /**
     * To get the total count of articles in the collection "articles"
     * */
    @GetMapping("/news/count")
    public Map<String,Long> count(){
        Map<String,Long> map = new HashMap<>();
        map.put("count",service.count());
        return map;
    }

    /**
    * To add an article to Favourites collection of the user
    * */
    @GetMapping("/news/like/{id}")
    public Article findTopNewsandLike(@PathVariable int id){

        return service.findbyIdtoLike(id);

    }

    /**
    * To remove an article from the Favourites collection of the user
    * */
    @GetMapping("/news/dislike/{id}")
    public Article  findTopNewsandDislike(@PathVariable int id){

        return service.findbyIdtodisike(id);


    }
}
