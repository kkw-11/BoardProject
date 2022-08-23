package com.example.board.config;

import com.example.board.repository.ArticleRepository;
import com.example.board.service.ArticleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final ArticleRepository articleRepository;

    public SpringConfig(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    @Bean
    public ArticleService articleService(){ return new ArticleService(articleRepository);}
}
