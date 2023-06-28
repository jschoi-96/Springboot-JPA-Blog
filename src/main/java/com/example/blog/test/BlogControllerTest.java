package com.example.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 스프링이 com.example.blog 패키지 이하를 스캔
// 특정 어노테이션이 붙어있는 클래스 파일들을 new해서 (IoC)해서 스프링 컨테이너에서 관리해줌
@RestController
public class BlogControllerTest {

    @GetMapping("/test/hello")
    public String Hello() {
        return "<h1> Hello spring boot </h1>";
    }
}
