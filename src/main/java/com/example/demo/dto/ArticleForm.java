package com.example.demo.dto;


import com.example.demo.entity.Article;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class ArticleForm {

    @NotNull

    private Long id;
    @NotNull
    @Size(min=2, max=30)
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String author;

    public Article toEntity(){
        return new Article(id, title, content, author);
    }
}
