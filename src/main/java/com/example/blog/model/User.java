package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User 클래스가 MySQL에 테이블이 생성이 된
// @DynamicInsert // insert시에 null인 필드를 제외한다.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB에 넘버링 전략을 따라간다
    private int id; // 시퀀스, auto_increment

    @Column(nullable = false, length = 100 , unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // DB는 RoleType이란게 없음.
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum을 쓰는게 좋다 (admin, user, manager).. Enum설정을 하면 3개중 하나만 선택할 수 있게 가능

    @CreationTimestamp // 시간이 자동으로 입력됨
    private Timestamp createDate;


    private String oauth; // kakao, google
}
