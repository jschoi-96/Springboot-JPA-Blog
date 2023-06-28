package com.example.blog.service;

import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 함. IoC를 해줌
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Transactional
    public void signUp(User user) {
        String rawPassword = user.getPassword(); // 1234 원본
        String encPassword = encoder.encode(rawPassword); // 해쉬 완료

        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }


}
