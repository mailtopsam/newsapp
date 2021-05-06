package com.stackroute.NewsApp.Repository;

import com.stackroute.NewsApp.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
//marks the specific class as a Data Access Object
@Repository
public interface NewsRepository extends MongoRepository<Article, Integer> {

    @Query(value = "{}", count = true)
    public long count();

    //find article by source
    List<Article> findBySource(String source);

    //find article by country
    List<Article> findByCountry(String country) ;

    //find article by category
    List<Article> findByCategory(String category);

    //find article by country and category
    List<Article> findByCountryAndCategory(String country,String category);

    //get article by id
    @Query(value = "{id:?0}")
    Article findById(int id);

    //get likes of an article
    @Query(value = "{}",fields = "{_id:0}",sort = "{likes:-1}")
    List<Article> findByLikes();



}
