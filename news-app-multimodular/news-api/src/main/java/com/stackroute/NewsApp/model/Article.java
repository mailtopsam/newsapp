package com.stackroute.NewsApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//annotation for parameterised constructor
@AllArgsConstructor
//annotation for default constructor
@NoArgsConstructor
//annotations for getter and setter
@Getter
@Setter
@Document(collection = "articles")
public class Article {
    //properties
    /**
     * _id is indicated as the primary key
     * */
    @Id
    private ObjectId _id;
    private int id;
    private int likes;
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

