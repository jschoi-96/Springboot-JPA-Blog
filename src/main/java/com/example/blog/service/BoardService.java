package com.example.blog.service;

import com.example.blog.dto.ReplySaveRequestDto;
import com.example.blog.model.Board;
import com.example.blog.model.Reply;
import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.repository.BoardRepository;
import com.example.blog.repository.ReplyRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {


    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void write(Board board , User user) { // title, content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page <Board> articles(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }


    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Board getContent(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패, 아이디가 없습니다.");
                });
    }

        @org.springframework.transaction.annotation.Transactional
    public void deleteId(int id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 글수정하기(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                }); // 영속화 완료
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹 - 자동 업데이트가 됨. db flush
    }

    @Transactional
    public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {

        replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());

//        User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()->{
//            return new IllegalArgumentException("댓글 쓰기 실패: 유저 id를 찾을 수 없음");
//        }); // 영속화 완료
//
//        Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{
//            return new IllegalArgumentException("댓글 쓰기 실패: 게시글 id를 찾을 수 없음");
//        });
//
//        Reply reply = Reply.builder()
//                .user(user)
//                .board(board)
//                .content(replySaveRequestDto.getContent())
//                .build();

    }

    @javax.transaction.Transactional
    public void 댓글삭제(int replyId){
        replyRepository.deleteById(replyId);
    }
}
