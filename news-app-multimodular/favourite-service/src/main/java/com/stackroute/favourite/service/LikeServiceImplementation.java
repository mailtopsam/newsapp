package com.stackroute.favourite.service;

import com.stackroute.favourite.model.UserLikes;
import com.stackroute.favourite.repository.LikeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;
//annotation for logging
@Slf4j
//indicates annotated class is a service which hold business logic in the Service layer
@Service
//annotation for parameterised constructor using lombok dependency
@AllArgsConstructor
public class LikeServiceImplementation implements LikeService{


    private static final Logger logger = (Logger) LoggerFactory.getLogger(LikeServiceImplementation.class);

    @Autowired
    private LikeRepository likeRepository;

    //Implementation of get all likes method
    @Override
    public List<UserLikes> getAllLikes(String emailId ){
        logger.info("These are the favourites of "+emailId);
        return likeRepository.findByEmailId(emailId);

    }

    //Implementation of delete user likes method
    @Override
    public String deleteLike(UserLikes userLikes) {
       UserLikes user = likeRepository.findByEmailAndFavourite_index(userLikes.getEmailId(), userLikes.getId());
       if (user != null) {
           likeRepository.delete(user);
           logger.info("The mentioned article has been removed from Favourites of"+userLikes.getEmailId());
           return "Deleted Like";
       }
        logger.info("The mentioned article does not exist in Favourites");
        return "Does not Exist";
    }

    //Implementation of add user likes method
    @Override
    public String addLike(UserLikes userLikes) {
        UserLikes user = likeRepository.findByEmailAndFavourite_index(userLikes.getEmailId(), userLikes.getId());
        if (user==null) {
            logger.info("The article has been saved to Favourites of " + userLikes.getEmailId());
            likeRepository.save(userLikes);
            return "Saved your Like";
        }
        return "Already Exists";
    }
}
