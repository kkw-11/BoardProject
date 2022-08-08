package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;
    private String content;
}
