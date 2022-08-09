package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@ToString
@NoArgsConstructor
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String author;
}
