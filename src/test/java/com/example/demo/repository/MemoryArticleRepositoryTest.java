package com.example.demo.repository;

import com.example.demo.entity.Article;
import com.example.demo.repository.MemoryArticleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryArticleRepositoryTest {
    MemoryArticleRepository repository = new MemoryArticleRepository();

    @AfterEach()
    public void afterEach(){
        repository.clearStore();
    }



    @Test
    public void save(){
        Article article = new Article();
        article.setTitle("This is title");

        repository.save(article);
        System.out.println(article.toString());

        Article result = repository.findById(article.getId()).get();
        System.out.println(article.getId());

        assertThat(article).isEqualTo(result);
    }

}
