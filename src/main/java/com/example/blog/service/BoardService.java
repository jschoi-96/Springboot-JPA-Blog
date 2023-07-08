package com.example.blog.service;

import com.example.blog.model.Board;
import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.repository.BoardRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BoardService {


    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void write(Board board , User user) { // title, content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    public Page <Board> articles(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
}
