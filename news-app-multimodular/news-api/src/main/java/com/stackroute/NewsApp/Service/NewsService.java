package com.stackroute.NewsApp.Service;

import com.stackroute.NewsApp.model.Article;

import java.util.List;

public interface NewsService {

    public long count();

    //find article by country
    List<Article> findByCountry(String country) ;

    //find article by category
    List<Article> findByCategory(String category) ;

    //find news from source
    List<Article> findBySource(String source) ;

    //find article by country and category
    List<Article> findByCountryAndCategory(String country, String category);

    //find news by position
    Article findNewsByPosition(int position);

    //get all articles
    List<Article> findAllArticles();

    //find id to like
    Article findbyIdtoLike(int id);

    //find id to dislike
    Article findbyIdtodisike(int id);

    //get all articles by like
    List<Article> findByLikes();





}
