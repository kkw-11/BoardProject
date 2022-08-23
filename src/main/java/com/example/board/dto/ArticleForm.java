package com.example.board.dto;


import com.example.board.entity.Article;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
public class ArticleForm {

    @NotNull
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String author;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Article toEntity(){
        return new Article(id, title, content, author, createdDate, modifiedDate);
    }
}