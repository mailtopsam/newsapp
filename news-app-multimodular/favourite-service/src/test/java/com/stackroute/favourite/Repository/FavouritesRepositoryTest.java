package com.stackroute.favourite.Repository;
import com.stackroute.favourite.model.UserLikes;
import com.stackroute.favourite.repository.LikeRepository;
import com.stackroute.favourite.service.LikeService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FavouritesRepositoryTest {

    @Autowired
    private LikeRepository repository;
    private UserLikes userLikes;
    private LikeService service;

    @BeforeEach
    public void setUp() {
        userLikes = new UserLikes();
        userLikes.set_id(ObjectId.get());
        userLikes.setAuthor("Rachana Deshai");
        userLikes.setLikes(6);
        userLikes.setCountry("in");
        userLikes.setCategory("entertainment");
        userLikes.setSource("times-of-india");
        userLikes.setDescription("Covid-19 outbreak in India....");
        userLikes.setUrl("https:/covid-19/india/times-of-india");
        userLikes.setEmailId("pooja@gmail.com");
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
        userLikes = null;
    }

    @Test
    public void getAllArticles() {
        repository.save(userLikes);
        assertEquals(6, userLikes.getLikes());
    }
    @Test
    public void findByEmailId() {
        List<UserLikes> list = repository.findByEmailId(userLikes.getEmailId());
        list.add(userLikes);
        assertEquals("pooja@gmail.com", list.get(0).getEmailId());
    }
    @Test
    public void findByEmailandFavourite(){
        repository.save(userLikes);
        UserLikes userLikes1 = repository.findByEmailAndFavourite_index(userLikes.getEmailId(), userLikes.getId());
        assertEquals(userLikes.getCountry(),userLikes1.getCountry());
    }
    @Test
   public void deleteLike(){
        repository.save(userLikes);
        repository.delete(userLikes);
        UserLikes userLikes1=repository.findByEmailAndFavourite_index(userLikes.getEmailId(), userLikes.getId());
        assertNull(userLikes1);

   }
    @Test
    public void addLike(){
        repository.save(userLikes);
        UserLikes userLikes1=repository.findByEmailAndFavourite_index(userLikes.getEmailId(), userLikes.getId());
        assertNotNull(userLikes1);

    }




}