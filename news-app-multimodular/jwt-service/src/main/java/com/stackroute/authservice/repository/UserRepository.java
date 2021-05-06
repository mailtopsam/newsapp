package com.stackroute.authservice.repository;

import com.stackroute.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//this class is implementing the JpaRepository interface for User
//marks the specific class as a Data Access Object
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public User findByIdAndPassword(String id, String password);

}
