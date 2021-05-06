package com.stackroute.favourite.Controller;

import com.stackroute.favourite.model.UserLikes;
import com.stackroute.favourite.service.LikeService;
import org.apache.catalina.User;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ControllerTest {

    private MockMvc mockMvc;
    @Mock
    LikeService service;
    @InjectMocks
    private Controller controller;

    private UserLikes userLikes;
    private List<UserLikes> list;
    @BeforeEach
    void setUp() {
        userLikes = new UserLikes();
        userLikes.setId(1);
        userLikes.setLikes(3);
        userLikes.setEmailId("sam.P@email.com");
        userLikes.setAuthor("Vickey Lalwani");
        userLikes.setCategory("entertainment");
        userLikes.setCountry("in");
        userLikes.setDescription("Rishi Kapoor passed away on April 30, 2020. It has been one year witho...");
        userLikes.setSource("the-times-of-india");
        userLikes.setTitle("Rishi Kapoor's Death Anniversary: 'I wish to see our sons, Ranbir and ...");
        userLikes.setUrl("https://timesofindia.indiatimes.com/entertainment/hindi/bollywood/news...");
        userLikes.setPublishedAt("2021-04-30T16:41:00Z");
        userLikes.setContent("Content");
        userLikes.setUrlToImage("https://static.toiimg.com/thumb/mssource-82332397,wsourceth-1070,heigh...");
        list = new ArrayList<>();
        list.add(userLikes);
    }
    @AfterEach
    void tearDown() {
        userLikes = null;
    }

    @Test
    public void givenGetAllUserLikesThenListShouldNotBeNull()  {
        List<UserLikes> list = service.getAllLikes(userLikes.getEmailId());
        assertNotNull(list);
    }

    @Test
    public void givenDislikeArticleThenReturn(){

    }

}