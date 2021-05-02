package com.portfolio.toy.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "member_auth")
public class MemberAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAuthNo;
    @Column(name = "user_no")
    private Long userNo;
    private String auth;
}