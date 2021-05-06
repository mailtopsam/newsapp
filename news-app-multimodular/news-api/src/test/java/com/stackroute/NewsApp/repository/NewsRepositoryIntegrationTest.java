package com.stackroute.NewsApp.repository;

import com.stackroute.NewsApp.Repository.NewsRepository;
import com.stackroute.NewsApp.model.Article;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class NewsRepositoryIntegrationTest {
    @Autowired
    private NewsRepository repository;
    private Article article;
    @BeforeEach
    public void setUp() {
        article = new Article();
        article.set_id(ObjectId.get());
        article.setId(1);
        article.setSource("the-times-of-india");
        article.setCategory("entertainment");
        article.setAuthor("Vickey Lalwani");
        article.setCountry("in");
        article.setContent("Rishi Kapoor passed away on April 30, 2020. It has been one year witho...");
        article.setTitle("Rishi Kapoor's Death Anniversary: 'I wish to see our sons, Ranbir and ...");
        article.setDescription("Rishi Kapoor passed away on April 30, 2020. It has been one year witho...");
        article.setPublishedAt("2021-04-30T16:41:00Z");
        article.setUrl("https://timesofindia.indiatimes.com/entertainment/hindi/bollywood/news...");
        article.setUrlToImage("https://static.toiimg.com/thumb/mssource-82332397,wsourceth-1070,heigh...");

    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
        article = null;
    }
    @Test
    public void getAllArticles() {
        Article article = new Article(ObjectId.get(),2,1 , "in", "entertainment","sportskeeda","Lennard Surrao","Randy Orton says a former WWE Champion shows up backstage regularly de...","Randy Orton is one of the most respected names in the business, and he...","https://www.sportskeeda.com/wwe/news-randy-orton-says-former-wwe-champ...","https://staticg.sportskeeda.com/editor/2021/04/33ed6-16197978483804-80...","2021-04-30T16:10:35Z","Randy Orton is one of the most respected names in the business, and he...");;
        Article article1 = new Article(ObjectId.get(),3,4,"in","entertainment","hindustantimes","HT Entertainment Desk","Kangana Ranaut reacts as Twitter user says she's not helping needy ams...","Kangana Ranaut hit back at two Twitter users, one of whom suggested th...","https://www.hindustantimes.com/entertainment/bollywood/kangana-ranaut-...","https://images.hindustantimes.com/img/2021/04/30/1600x900/Kangana1_161...","2021-04-30T14:50:26Z", "Kangana Ranaut shot back at Twitter users who questioned her role in h...");
        repository.save(article);
        repository.save(article1);

        List<Article> list = (List<Article>) repository.findAll();
        assertEquals("entertainment", list.get(1).getCategory());
    }

    @Test
    public void givenSourceThenShouldReturnArticles() {
        Article article = new Article(ObjectId.get(),1,5,"in","business","ndtv","Arushi Jain","Milind Soman,Ready To Donate Plasma In Another 10 Days, Wrote This ...","Milind Soman is currently recuperating after testing positive for COVs...","https://www.ndtv.com/entertainment/milind-soman-ready-to-donate-plasma...","https://c.ndtvimg.com/2021-04/f49h0jf_milind-soman-_625x300_30_April_2...","2021-04-30T12:38:56Z","Milind Soman shared this image. (courtesy milindusharunning)Highligh...");
        Article article1 = repository.save(article);
        List<Article> list = repository.findBySource(article1.getSource());
        assertEquals(article1.getSource(), list.get(0).getSource());
        assertEquals(article1.get_id(), list.get(0).get_id());
        assertEquals(article1.getCountry(), list.get(0).getCountry());
        assertEquals(article.getCategory(), list.get(0).getCategory());
        assertEquals(article1.getAuthor(), list.get(0).getAuthor());
        assertEquals(article1.getTitle(), list.get(0).getTitle());
        assertEquals(article1.getDescription(), list.get(0).getDescription());
        assertEquals(article1.getUrl(), list.get(0).getUrl());
        assertEquals(article1.getUrlToImage(), list.get(0).getUrlToImage());
        assertEquals(article1.getPublishedAt(), list.get(0).getPublishedAt());
        assertEquals(article1.getContent(), list.get(0).getContent());
    }
    @Test
    public void givenCategoryThenShouldReturnArticles() {
        Article article = new Article(ObjectId.get(),5,3,"in","entertainment","gulte","Satya","Sreekaram Emerges As Winner on OTT - Gulte Sreekaram - Gulte","Sharwanand's Sreekaram recently had its digital premiere on Sun Nxt, t...","https://www.gulte.com/movienews/84155/sreekaram-emerges-as-winner-on-o...","https://cdn.gulte.com/wp-content/uploads/2021/03/Sreekaram-1-660x330.j...","2021-04-30T12:23:20Z","Sharwanand’s Sreekaram recently had its digital premiere on Sun Nxt, t...");
        Article article1 = repository.save(article);
        List<Article> list = repository.findByCategory(article1.getCategory());
        assertEquals(article1.getSource(), list.get(0).getSource());
        assertEquals(article1.get_id(), list.get(0).get_id());
        assertEquals(article1.getCountry(), list.get(0).getCountry());
        assertEquals(article.getCategory(), list.get(0).getCategory());
        assertEquals(article1.getAuthor(), list.get(0).getAuthor());
        assertEquals(article1.getTitle(), list.get(0).getTitle());
        assertEquals(article1.getDescription(), list.get(0).getDescription());
        assertEquals(article1.getUrl(), list.get(0).getUrl());
        assertEquals(article1.getUrlToImage(), list.get(0).getUrlToImage());
        assertEquals(article1.getPublishedAt(), list.get(0).getPublishedAt());
        assertEquals(article1.getContent(), list.get(0).getContent());
    }
    @Test
    public void givenCountryThenShouldReturnArticles() {
        Article article = new Article(ObjectId.get(),8,2,"in","entertainment","the-times-of-india","Neha Maheshwri","Aniruddh Dave is in the ICU, but his health is improving: Ajay Singh C...","Actor Aniruddh Dave, who was last seen in Shakti - Astitva Ke Ehsaas K...","https://timesofindia.indiatimes.com/tv/news/hindi/aniruddh-dave-is-in-...","https://static.toiimg.com/thumb/mssource-82328667,wsourceth-1070,heigh...","2021-04-30T11:24:00Z","Actor Aniruddh Dave, who was last seen in Shakti - Astitva Ke Ehsaas K...");
        Article article1 = repository.save(article);
        List<Article> list = repository.findByCountry(article1.getCountry());
        assertEquals(article1.getSource(), list.get(0).getSource());
        assertEquals(article1.get_id(), list.get(0).get_id());
        assertEquals(article1.getCountry(), list.get(0).getCountry());
        assertEquals(article.getCategory(), list.get(0).getCategory());
        assertEquals(article1.getAuthor(), list.get(0).getAuthor());
        assertEquals(article1.getTitle(), list.get(0).getTitle());
        assertEquals(article1.getDescription(), list.get(0).getDescription());
        assertEquals(article1.getUrl(), list.get(0).getUrl());
        assertEquals(article1.getUrlToImage(), list.get(0).getUrlToImage());
        assertEquals(article1.getPublishedAt(), list.get(0).getPublishedAt());
        assertEquals(article1.getContent(), list.get(0).getContent());
    }

    @Test
    public void givenCountryAndCategoryThenShouldReturnArticles() {
        Article article = new Article(ObjectId.get(),5,2,"in","entertainment","the-times-of-india","Neha Maheshwri","Aniruddh Dave is in the ICU, but his health is improving: Ajay Singh C...","Actor Aniruddh Dave, who was last seen in Shakti - Astitva Ke Ehsaas K...","https://timesofindia.indiatimes.com/tv/news/hindi/aniruddh-dave-is-in-...","https://static.toiimg.com/thumb/mssource-82328667,wsourceth-1070,heigh...","2021-04-30T11:24:00Z","Actor Aniruddh Dave, who was last seen in Shakti - Astitva Ke Ehsaas K...");
        Article article1 = repository.save(article);
        List<Article> list = repository.findByCountryAndCategory(article1.getCountry(),article1.getCategory());
        assertEquals(article1.getSource(), list.get(0).getSource());
        assertEquals(article1.get_id(), list.get(0).get_id());
        assertEquals(article1.getCountry(), list.get(0).getCountry());
        assertEquals(article.getCategory(), list.get(0).getCategory());
        assertEquals(article1.getAuthor(), list.get(0).getAuthor());
        assertEquals(article1.getTitle(), list.get(0).getTitle());
        assertEquals(article1.getDescription(), list.get(0).getDescription());
        assertEquals(article1.getUrl(), list.get(0).getUrl());
        assertEquals(article1.getUrlToImage(), list.get(0).getUrlToImage());
        assertEquals(article1.getPublishedAt(), list.get(0).getPublishedAt());
        assertEquals(article1.getContent(), list.get(0).getContent());
    }
    @Test
    public void getArticleFromSource() {
        Article article = new Article(ObjectId.get(),1, 6, "in", "entertainment","sportskeeda","Lennard Surrao","Randy Orton says a former WWE Champion shows up backstage regularly de...","Randy Orton is one of the most respected names in the business, and he...","https://www.sportskeeda.com/wwe/news-randy-orton-says-former-wwe-champ...","https://staticg.sportskeeda.com/editor/2021/04/33ed6-16197978483804-80...","2021-04-30T16:10:35Z","Randy Orton is one of the most respected names in the business, and he...");;
        repository.save(article);
        Article fetchedArticle =  repository.findBySource(article.getSource()).get(0);
        assertEquals("entertainment", article.getCategory());
    }

    @Test
    public void getArticleFromCountry() {
        Article article = new Article(ObjectId.get(),1,7,  "in", "entertainment","sportskeeda","Lennard Surrao","Randy Orton says a former WWE Champion shows up backstage regularly de...","Randy Orton is one of the most respected names in the business, and he...","https://www.sportskeeda.com/wwe/news-randy-orton-says-former-wwe-champ...","https://staticg.sportskeeda.com/editor/2021/04/33ed6-16197978483804-80...","2021-04-30T16:10:35Z","Randy Orton is one of the most respected names in the business, and he...");;
        Article article1 = new Article(ObjectId.get(),2,1,"in","entertainment","hindustantimes","HT Entertainment Desk","Kangana Ranaut reacts as Twitter user says she's not helping needy ams...","Kangana Ranaut hit back at two Twitter users, one of whom suggested th...","https://www.hindustantimes.com/entertainment/bollywood/kangana-ranaut-...","https://images.hindustantimes.com/img/2021/04/30/1600x900/Kangana1_161...","2021-04-30T14:50:26Z", "Kangana Ranaut shot back at Twitter users who questioned her role in h...");
        repository.save(article);
        Article fetchedArticle =  repository.findBySource(article.getSource()).get(0);
        assertEquals("entertainment", article.getCategory());
    }


}
