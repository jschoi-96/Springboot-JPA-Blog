package com.example.blog.service;

import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
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

    @Transactional
    public void 회원수정(User user){
        // 수정 시에는 영속성 컨텍스트 User 오브젝트를 영속화 시키고
        // 영속화된 User 오브젝트를 수정
        User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
            return new IllegalArgumentException("회원 찾기에 실패하였습니다.");
        });

        // Validate 체크 => oauth 필드에 값이 없으면 수정 가능
        if (persistance.getOauth() == null || persistance.getOauth().equals("")) {
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword);
            persistance.setPassword(encPassword);

            persistance.setEmail(user.getEmail());
        }




        // 회원수정 함수 종료 = 서비스 종료 = 트랜잭션 종료 (commit 됨)

    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public User 회원찾기(String username){
        User user = userRepository.findByUsername(username).orElseGet(() -> {
            return new User();
        });
        return user;
    }

}
