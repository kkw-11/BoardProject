package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@RequiredArgsConstructor
class ArticleServiceTest {
    ArticleService articleService;
    ArticleRepository articleRepository;

    @Test
    void registerArticleTest(){
        Article article = new Article();
        article.setTitle("제목");
        article.setContent("내용");
        article.setAuthor("작성자");
        Long savedId = articleService.register(article);
        System.out.println("hello");

//        Article findedArticle = articleService.findOne(savedId).get();
//
//        assertThat(article.getTitle()).isEqualTo(findedArticle.getTitle());
//        assertThat(article.getContent()).isEqualTo(findedArticle.getContent());
//        assertThat(article.getAuthor()).isEqualTo(findedArticle.getAuthor());
    }

}
