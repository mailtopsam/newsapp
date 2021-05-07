package com.stackroute.favourite.repository;

import com.stackroute.favourite.model.UserLikes;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//marks the specific class as a Data Access Object
@Repository
public interface LikeRepository extends MongoRepository<UserLikes, ObjectId> {
    //get favourite articles by user Id
    @Query(value = "{emailId :?0}",fields = "{_id:0}")
    public List<UserLikes> findByEmailId(String email);
    //get user article from email Id and favourite index
    @Query("{emailId :?0 ,id:?1}")
    public UserLikes findByEmailAndFavourite_index(String email, int id);
}
