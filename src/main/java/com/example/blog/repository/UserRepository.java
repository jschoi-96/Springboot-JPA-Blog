package com.example.blog.repository;

import com.example.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// 자동으로 빈 등록이 됨
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional <User> findByUsername(String username);


}
    // JPA naming strategy
    // User findByUsernameAndPassword(String username, String password);

//    @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2" , nativeQuery = true)
//    User login(String username, String password);
