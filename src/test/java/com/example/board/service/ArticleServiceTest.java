package com.example.board.service;

import com.example.board.entity.Article;
import com.example.board.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleRepository articleRepository;

    @Test
    void registerTest(){
        Article article = new Article();
        article.setTitle("제목!!");
        article.setContent("내용!!");
        article.setAuthor("작성자!!");
        Long savedId = articleService.register(article);

        Article findedArticle = articleService.findOne(savedId).get();

        assertThat(article.getTitle()).isEqualTo(findedArticle.getTitle());
        assertThat(article.getContent()).isEqualTo(findedArticle.getContent());
        assertThat(article.getAuthor()).isEqualTo(findedArticle.getAuthor());
    }
    @Test
    void findOneTest(){
        Article article = new Article();
        article.setTitle("제목!!");
        article.setContent("내용!!");
        article.setAuthor("작성자!!");
        Long savedId = articleService.register(article);
        Long findedFristId = article.getId();
        Long findedSecondId = articleService.findOne(article.getId()).orElse(null).getId();

        assertThat(findedFristId).isEqualTo(findedSecondId);

    }


}
