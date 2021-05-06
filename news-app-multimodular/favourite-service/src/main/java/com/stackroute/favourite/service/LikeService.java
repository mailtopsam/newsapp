package com.stackroute.favourite.service;

import com.stackroute.favourite.model.UserLikes;
import org.bson.types.ObjectId;

import java.util.List;

public interface LikeService {
    //get all articles from favourites
    public List<UserLikes> getAllLikes(String emailId);
    //delete articles from favourites
    public String deleteLike(UserLikes userLikes);
    //add articles to favourites
    public String addLike(UserLikes userLikes);


}
