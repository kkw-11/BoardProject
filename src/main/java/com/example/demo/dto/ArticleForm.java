package com.example.demo.dto;


import com.example.demo.entity.Article;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {

    private Long id;
    private String title;
    private String content;
    private String author;

    public Article toEntity(){
        return new Article(id, title, content, author);
    }
}
