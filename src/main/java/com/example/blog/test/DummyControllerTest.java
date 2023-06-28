package com.example.blog.test;

import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// html 파일이 아니라 데이터를 return 해주는 컨트롤러
@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입 (DI)
    private UserRepository userRepository;


    @DeleteMapping("/dummy/user/{id}")
    public String deleteUser(@PathVariable int id ) {


        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제할 다이터가 없습니다.";
        }

        return "삭제되었습니다.";
    }

    // save 함수는 id를 전달하지 않으면 insert를 해주고
    // save 함수는 id를 전달하고 데이터가 있으면 update 해주고
    // id에 대한 데이터가 없으면 insert를 함
    // email, password 받아야 함.

    @Transactional // 함수 종료시에 자동 commit이 된다.
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id , @RequestBody User requestUser) { // json으로 데이터 요청 -> java object
        System.out.println("id = " + id);
        System.out.println("password = " + requestUser.getPassword());
        System.out.println("email = " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        // userRepository.save(user);

        // 더티 체킹
        return user;
    }


    //
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한 페이지 당 두건의 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public Page <User> pageList(@PageableDefault(size = 2 , sort = "id" , direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }

    // {id} 주소로 파라메터를 전달 받을 수 있음
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // id값을 찾지 못할떄, user가 null이 되므로
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 사용자는 없습니다.");
        });
        // 요청: 웹 브라우저
        // user 객체 = java object
        // 변환 (웹사이트가 이해할 수 있게) -> json
        // 스트링 부트 (MessageConverter라는 애가 응답시 자동 작동)
        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user) { // key=value (약속된 규칙)
        System.out.println("username = " + user.getUsername());
        System.out.println("password = " + user.getPassword());
        System.out.println("email = " + user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다";
    }
}
