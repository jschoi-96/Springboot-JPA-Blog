package com.example.blog.test;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

    @GetMapping("/temp/home")
    public String tempHome() {
        System.out.println("TempHome()");
        // 파일 리턴 기본경로: src/main/resources/static
        // 리턴명이 /home.html이 되어야함
        return "/home.html";
    }

}
