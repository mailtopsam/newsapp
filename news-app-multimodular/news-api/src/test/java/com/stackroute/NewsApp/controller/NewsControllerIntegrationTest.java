package com.stackroute.NewsApp.controller;

import com.stackroute.NewsApp.Controller.NewsController;
import com.stackroute.NewsApp.Service.NewsService;
import com.stackroute.NewsApp.model.Article;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)

public class NewsControllerIntegrationTest {
    private MockMvc mockMvc;
    @Mock
    NewsService service;
    @InjectMocks
    private NewsController controller;

    private Article article;
    private List<Article> list;
    @BeforeEach
    void setUp() {
        article = new Article(ObjectId.get(),1,4,"in","entertainment","the-times-of-india","Vickey Lalwani","Rishi Kapoor's Death Anniversary: 'I wish to see our sons, Ranbir and ...","Rishi Kapoor passed away on April 30, 2020. It has been one year witho...","https://timesofindia.indiatimes.com/entertainment/hindi/bollywood/news...","https://static.toiimg.com/thumb/mssource-82332397,wsourceth-1070,heigh...","2021-04-30T16:41:00Z","Rishi Kapoor passed away on April 30, 2020. It has been one year witho...");
        list = new ArrayList<>();
        list.add(article);
    }
    @AfterEach
    void tearDown() {
        article = null;
    }

    @Test
    public void givenGetAllArticlesThenListShouldNotBeNull()  {
        List<Article> list = service.findAllArticles();
        assertNotNull(list);
    }
    @Test
    void givenSourceThenShouldReturnArticles()  {
        List<Article> list1 = service.findBySource(article.getSource());
        list1.add(article);
        assertNotNull(list1);
        assertEquals(article.getCountry(), list1.get(0).getCountry());
    }
    @Test
    void givenCountryThenShouldReturnArticles()  {
        List<Article> list1 = service.findByCountry(article.getCountry());
        list1.add(article);
        assertNotNull(list1);
        assertEquals(article.getTitle(), list1.get(0).getTitle());
    }
    @Test
    void givenCategoryThenShouldReturnArticles()  {
        List<Article> list1 = service.findByCategory(article.getCategory());
        list1.add(article);
        assertNotNull(list1);
        assertEquals(article.getContent(), list1.get(0).getContent());
    }
    @Test
    void givenCountryAndCategoryThenShouldReturnArticles()  {
        List<Article> list1 = service.findByCountryAndCategory(article.getCountry(),article.getCategory());
        list1.add(article);
        assertNotNull(list1);
        assertEquals(article.getSource(), list1.get(0).getSource());
    }

}


