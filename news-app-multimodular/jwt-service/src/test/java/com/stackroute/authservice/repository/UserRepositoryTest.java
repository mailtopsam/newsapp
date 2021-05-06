package com.stackroute.authservice.repository;

import com.stackroute.authservice.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId("anjali@ust.com");
        user.setPassword("234lads5678");
        user.setFirstname("Anjali");
        user.setLastname("Ananth");
        user.setCountry("India");
        user.setLanguage("English");
        user.setProfileImage("https://bootdey.com/img/Content/avatar/avatar3.png");

    }
    @AfterEach
    void tearDown() {
        repository.deleteAll();
        user = null;
    }

    @Test
    public void givenUserProfileShouldSaveUserProfile(){
        repository.save(user);
        User userProfile= repository.findByIdAndPassword(user.getId(),user.getPassword());
        assertEquals("English",user.getLanguage());
    }
    @Test
    public void givenIdAndPasswordThenShouldReturnRespectiveUser() {
        User user1 = repository.save(user);
        User user2 = repository.findByIdAndPassword(user1.getId(), user1.getPassword());
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getPassword(), user2.getPassword());
        assertEquals(user1.getCountry(), user2.getCountry());
        assertEquals(user1.getFirstname(), user2.getFirstname());
    }

}