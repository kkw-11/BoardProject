package com.example.demo.config;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.ArticleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
public class SpringConfig {

    private final ArticleRepository articleRepository;

    public SpringConfig(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    @Bean
    public ArticleService articleService(){ return new ArticleService(articleRepository);}
}
