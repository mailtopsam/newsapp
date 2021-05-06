package com.stackroute.NewsApp.Service;
import com.stackroute.NewsApp.Repository.NewsRepository;
import com.stackroute.NewsApp.Service.NewsServiceImpl;
import com.stackroute.NewsApp.model.Article;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class NewsServiceTest {

    @Mock
    private NewsRepository repository;

    @InjectMocks
    private NewsServiceImpl service;
    private Article article, article1;
    private List<Article> list;
    private Optional optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        article = new Article(ObjectId.get(),1,4,"the-times-of-india","in","entertainment","Vickey Lalwani","Rishi Kapoor's Death Anniversary: 'I wish to see our sons, Ranbir and ...","Rishi Kapoor passed away on April 30, 2020. It has been one year witho...","https://timesofindia.indiatimes.com/entertainment/hindi/bollywood/news...","https://static.toiimg.com/thumb/mssource-82332397,wsourceth-1070,heigh...","2021-04-30T16:41:00Z","Rishi Kapoor passed away on April 30, 2020. It has been one year witho...");
        article1 = new Article(ObjectId.get(),2, 2, "sportskeeda", "in","entertainment","Lennard Surrao","Randy Orton says a former WWE Champion shows up backstage regularly de...","Randy Orton is one of the most respected names in the business, and he...","https://www.sportskeeda.com/wwe/news-randy-orton-says-former-wwe-champ...","https://staticg.sportskeeda.com/editor/2021/04/33ed6-16197978483804-80...","2021-04-30T16:10:35Z","Randy Orton is one of the most respected names in the business, and he...");
        optional = Optional.of(article);
    }

    @AfterEach
    public void tearDown() {
        article = null;
    }

    @Test
    public void givenCategoryToThenShouldReturnArticles() {
        list = new ArrayList<>();

        when(repository.findByCategory(any())).thenReturn(list);
        assertEquals(list, service.findByCategory(article.getCategory()));
        verify(repository, times(1)).findByCategory(any());
    }
    @Test
    public void givenCategoryThenShouldNotReturnArticles() {
        when(repository.findByCategory(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            service.findByCategory(article.getCategory());
        });
        verify(repository, times(1)).findByCategory(any());
    }
    @Test
    public void givenSourceThenShouldReturnArticles() {
        list = new ArrayList<>();

        when(repository.findBySource(any())).thenReturn(list);
        assertEquals(list, service.findBySource(article.getSource()));
        verify(repository, times(1)).findBySource(any());
    }
    @Test
    public void givenSourceThenShouldNotReturnArticles() {
        when(repository.findBySource(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            service.findBySource(article.getSource());
        });
        verify(repository, times(1)).findBySource(any());
    }
    @Test
    public void givenCountryThenShouldReturnArticles() {
        list = new ArrayList<>();

        when(repository.findByCountry(any())).thenReturn(list);
        assertEquals(list, service.findByCountry(article.getCountry()));
        verify(repository, times(1)).findByCountry(any());
    }
    @Test
    public void givenCountryThenShouldNotReturnArticles() {
        when(repository.findByCountry(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            service.findByCountry(article.getCountry());
        });
        verify(repository, times(1)).findByCountry(any());
    }
    @Test
    public void givenCountryAndCategoryThenShouldReturnArticles() {
        list = new ArrayList<>();

        when(repository.findByCountryAndCategory(any(),any())).thenReturn(list);
        assertEquals(list, service.findByCountryAndCategory(article.getCountry(),article.getCategory()));
        verify(repository, times(1)).findByCountryAndCategory(any(),any());
    }
    @Test
    public void givenCountryAndCategoryThenShouldNotReturnArticles() {
        when(repository.findByCountryAndCategory(any(),any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            service.findByCountryAndCategory(article.getCountry(),article.getCategory());
        });
        verify(repository, times(1)).findByCountryAndCategory(any(),any());
    }
    @Test
    public void getAllArticles() {
        repository.findBySource(article.getSource());
        when(repository.findAll()).thenReturn(list);
        List<Article> list1 = service.findAllArticles();
        assertEquals(list, list1);
        verify(repository, times(1)).findBySource(article.getSource());
        verify(repository, times(1)).findAll();
    }

}




