package com.example.techthink.persistence.repository;

import com.example.techthink.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.email=?1 or u.username = ?1")
    User loadUserByUsernameOrEmail(String term);
}
