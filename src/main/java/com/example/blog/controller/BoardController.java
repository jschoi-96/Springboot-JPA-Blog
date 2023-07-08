package com.example.blog.controller;

import com.example.blog.config.auth.PrincipalDetail;
import com.example.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;


    // @AuthenticationPrincipal PrincipalDetail principal
    @GetMapping({"" , "/"})
    public String index(Model model , @PageableDefault(size = 3 , sort = "id" , direction = Sort.Direction.DESC)Pageable pageable) {
        model.addAttribute("boards" , boardService.articles(pageable));
        return "index"; // viewResolver 작동.
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
}
