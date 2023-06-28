package com.example.blog.test;

import org.springframework.web.bind.annotation.*;

// 사용자가 요청 -> 응답 (HTML)
// @Controller

// 사용자가 요청 -> 응답 (Data)
@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest: ";

    @GetMapping("/http/lombok")
    public String lombokTest() {
//        Member m = new Member(1, "ssar", "1234", "email");
        Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
        System.out.println(TAG + " getter: " + m.getId());
        m.setId(5000);
        System.out.println(TAG + " setter: " + m.getId());
        return "lombok Test finished";
    }
    // 인터넷 브라우저 요청은 무조건 get 밖에 할 수 없다.
    // http://localhost:8080/http/get (select 방식) 데이터를 읽어온다. 서버로 부터
    @GetMapping("/http/get")
    public String getTest(Member m) {

        return "get 요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8080/http/post (Insert 방식) 데이터를 추가해준다.
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m) {
        return "post 요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // // http://localhost:8080/http/put (update)
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put 요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8080/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }
}
