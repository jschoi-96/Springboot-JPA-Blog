package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @Column(nullable = false , length = 100)
    private String title;

    @Lob // 대용량 대이터
    private String content; // 섬머노트 라이브러리 -> html 태그가 섞여서 디자인이

    private int count; // 조회 수

    @ManyToOne (fetch = FetchType.EAGER)// Many = Board, One = user
    @JoinColumn(name = "userId")
    private User user; // DB는 오브젝트 저장x, FK,자바는 오트젝트 저장 o

    @OneToMany(mappedBy = "board" , fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy 연관관계 주인이 아니 (FK가 아니다), db에 컬럼 만들기 x
    @JsonIgnoreProperties({"board"})  // 무한참조를 방지하기 위해서
    @OrderBy("id desc")
    private List<Reply> replys;

    @CreationTimestamp
    private Timestamp createDate;

}
