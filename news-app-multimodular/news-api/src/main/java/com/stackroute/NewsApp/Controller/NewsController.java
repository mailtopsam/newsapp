package com.stackroute.NewsApp.Controller;
import com.stackroute.NewsApp.Service.NewsService;
import com.stackroute.NewsApp.model.Article;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//lombok annotation for parameterised constructor
@AllArgsConstructor
//annotation is used to create Restful web services using Spring MVC
@RestController
@RequestMapping("api/v1")
@Slf4j
public class NewsController {
    @Autowired
    private NewsService service;

    /**
     * To find articles by its source eg:bbcnews
     * */
    @GetMapping("/news/source/{source}")
    public List<Article> findBySource(@PathVariable String source){
        log.info("findBySource service called");
        return service.findBySource(source);
    }

    /**
     * To get articles by the country eg:in for India
     * */
    @GetMapping("/news/country/{country}")
    public List<Article> findByCountry(@PathVariable String country){
        log.info("findByCountry service is called");
        return service.findByCountry(country);
    }

    /**
     * To get articles by category eg:business
     * */
    @GetMapping("/news/category/{category}")
    public List<Article> findByCategory(@PathVariable String category){
        log.info("findByCategory service is called");
        return service.findByCategory(category);
    }

    /**
     * To get articles by its category and country
     * */
    @GetMapping("/news/country/{country}/category/{category}")
    public List<Article> findByCountryAndCategory(@PathVariable String country, @PathVariable String category){
        log.info("findByCountryAndCategory service is called ");
        return service.findByCountryAndCategory(country,category);
    }

    /**
    * To get a particular article from a country and category by id
    * */
    @GetMapping("/news/country/{country}/category/{category}/{id}")
    public Article onlyOneArticleFromCountryAndCategory(@PathVariable String country, @PathVariable String category,@PathVariable int id){
        log.info("findByCountryAndCategory service is called, One Article is returned by id");
       List<Article> list = service.findByCountryAndCategory(country,category);
       return list.get(id);

    }

    /**
     * To get all the trending news (based on the number of likes)
     * */
    @GetMapping("trending-news")
    public List<Article> trendingNews(){
        log.info("findByLikes service is called");
        return service.findByLikes();
    }

    /**
     * To get all the articles in the collection "articles"*/
    @GetMapping("/news")
    public List<Article> findAll(){
        log.info("findAllArticles service is called");
        return service.findAllArticles();
    }




}