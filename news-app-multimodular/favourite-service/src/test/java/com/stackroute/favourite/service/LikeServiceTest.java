package com.stackroute.favourite.service;

import com.stackroute.favourite.model.UserLikes;
import com.stackroute.favourite.repository.LikeRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {
    @Mock
    private LikeRepository repository;

    @InjectMocks
    private LikeServiceImplementation service;
    private List<UserLikes> list;
    private UserLikes userLikes;
    private Optional optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userLikes = new UserLikes();
        userLikes.set_id(ObjectId.get());
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

        optional = Optional.of(userLikes);
    }

    @AfterEach
    public void tearDown() {
        userLikes = null;
    }

    @Test
    public void givenEmailIdGetAllLikes() {
        when(repository.findByEmailId(any())).thenReturn(list);
        assertEquals(list,repository.findByEmailId(userLikes.getEmailId()));
        verify(repository, times(1)).findByEmailId(any());
    }

    @Test
    public void givenUserLikesDeleteLikesReturnDoesNotExist() {
        repository.save(userLikes);
        repository.delete(userLikes);
//        when(repository.delete(any()));
        String result = service.deleteLike(userLikes);
        assertEquals("Does not Exist",result);
        verify(repository, times(1)).delete(any());
    }

//    @Test
//    public void givenUserLikesDeleteLikesReturn() {
//        when(repository.save(any())).thenReturn(userLikes);
//        repository.save(userLikes);
////        UserLikes userLikes1= repository.findbyemailandfavourite_index(userLikes.getEmailId(),userLikes.getId());
////        assertEquals("sam.P@email.com",userLikes1.getEmailId());
////        repository.delete(userLikes);
//        String result = service.deleteLike(userLikes);
//        assertEquals("Deleted Like",result);
//        verify(repository, times(1)).delete(userLikes);
//    }

    @Test
    public void givenUserLikesAddLikes() {
        when(repository.save(any())).thenReturn(userLikes);
        String result = service.addLike(userLikes);
        assertEquals("Saved your Like",result);
        verify(repository, times(1)).save(any());
    }
}