package com.stackroute.NewsApp.Service;

import com.stackroute.NewsApp.Repository.NewsRepository;
import com.stackroute.NewsApp.model.Article;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@AllArgsConstructor
//indicates annotated class is a service which hold business logic in the Service layer
@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    private NewsRepository repository;

    //implement findBySource method
    @Override
    public List<Article> findBySource(String source) {
        return repository.findBySource(source);
    }

    @Override
    public long count() {
        return repository.count();
    }

    //implement findByCountry method
    @Override
    public List<Article> findByCountry(String country){
        return repository.findByCountry(country);
    }

    //implement findByCategory method
    @Override
    public List<Article> findByCategory(String category){
        return repository.findByCategory(category);
    }

    //implement findByCountryAndCategory method
    @Override
    public List<Article> findByCountryAndCategory(String country,String category){
        return repository.findByCountryAndCategory(country,category);
    }

    //implement findNewsByPosition method
    @Override
    public Article findNewsByPosition(int position){
        return  repository.findAll().get(position);
    }

    //implement findAllArticles method
    @Override
    public List<Article> findAllArticles() {
        return repository.findAll();
    }

    //implement findbyIdtoLike method
    @Override
    public Article findbyIdtoLike(int id) {
       Article article = repository.findById(id);
       int likes = article.getLikes();
       article.setLikes(likes+1);
      return repository.save(article);

    }
    //implement findbyIdtodisike method
    @Override
    public Article findbyIdtodisike(int id) {
        Article article = repository.findById(id);
        int likes = article.getLikes();
        if (likes >= 1 ) {
            article.setLikes(likes-1);

        }
        return repository.save(article);
    }
    //implement findByLikes method

    @Override
    public List<Article> findByLikes() {
        return repository.findByLikes();
    }

}
