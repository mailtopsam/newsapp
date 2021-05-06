package com.stackroute.NewsApp.Service;

import com.stackroute.NewsApp.Repository.NewsRepository;
import com.stackroute.NewsApp.Service.NewsServiceImpl;
import com.stackroute.NewsApp.model.Article;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class NewsServiceIntegrationTest {
    @Autowired
    private NewsRepository repository;

    @Autowired
    private NewsServiceImpl service;
    private Article article1, article2, article3;
    private List<Article> list;
    private Optional optional;

    @BeforeEach
    public void setUp() {

        list = new ArrayList<>();
        article1 = new Article(ObjectId.get(), 4,1,"in","entertainment","the-times-of-india","Vickey Lalwani","Rishi Kapoor's Death Anniversary: 'I wish to see our sons, Ranbir and ...","Rishi Kapoor passed away on April 30, 2020. It has been one year witho...","https://timesofindia.indiatimes.com/entertainment/hindi/bollywood/news...","https://static.toiimg.com/thumb/mssource-82332397,wsourceth-1070,heigh...","2021-04-30T16:41:00Z","Rishi Kapoor passed away on April 30, 2020. It has been one year witho...");
        article2 = new Article(ObjectId.get(), 1,2,  "in", "entertainment","sportskeeda","Lennard Surrao","Randy Orton says a former WWE Champion shows up backstage regularly de...","Randy Orton is one of the most respected names in the business, and he...","https://www.sportskeeda.com/wwe/news-randy-orton-says-former-wwe-champ...","https://staticg.sportskeeda.com/editor/2021/04/33ed6-16197978483804-80...","2021-04-30T16:10:35Z","Randy Orton is one of the most respected names in the business, and he...");
        article3 = new Article(ObjectId.get(), 4,3,"in","entertainment","hindustantimes","HT Entertainment Desk","Kangana Ranaut reacts as Twitter user says she's not helping needy ams...","Kangana Ranaut hit back at two Twitter users, one of whom suggested th...","https://www.hindustantimes.com/entertainment/bollywood/kangana-ranaut-...","https://images.hindustantimes.com/img/2021/04/30/1600x900/Kangana1_161...","2021-04-30T14:50:26Z", "Kangana Ranaut shot back at Twitter users who questioned her role in h...");
        list.add(article1);
        list.add(article2);
        list.add(article3);
    }

    @AfterEach
    public void tearDown() {
        article1 = article2 = article3 = null;
        list = null;
    }
    @Test
    public void givenGetAllArticlesThenShouldReturnListOfAllArticles() {
        List<Article> list = (List<Article>) repository.findAll();
        assertNotNull(list);
    }
    @Test
    public void givenCategoryThenShouldReturnArticles() {
        List<Article> list1 = repository.findByCategory(article1.getCategory());
        list1.add(article1);
        assertNotNull(list1);
        assertEquals(article1.getSource(), list1.get(0).getSource());
    }
    @Test
    public void givenCountryThenShouldReturnArticles() {
        List<Article> list1 = repository.findByCountry(article1.getCountry());
        list1.add(article1);
        assertNotNull(list1);
        assertEquals(article1.getAuthor(), list1.get(0).getAuthor());
    }
    @Test
    public void givenSourceThenShouldReturnArticles() {
        List<Article> list1 = repository.findBySource(article1.getSource());
        list1.add(article1);
        assertNotNull(list1);
        assertEquals(article1.get_id(), list1.get(0).get_id());
    }
    @Test
    public void givenCountryAndCategoryThenShouldReturnArticles() {
        List<Article> list1 = repository.findByCountryAndCategory(article1.getCountry(),article1.getCategory());
        list1.add(article1);
        assertNotNull(list1);
        assertEquals(article1.getSource(), list1.get(0).getSource());
    }


}