package com.stackroute.favourite.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//annotation for default constructor using lombok
@NoArgsConstructor
//getter and setter
@Data
@Document(collection = "userlikes")
public class UserLikes {
// makes id variable as Primary key
    @Id
    private ObjectId _id;
    private int id;
    private int likes;
    private String emailId;
    private String country;
    private String category;
    private String source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;



}
