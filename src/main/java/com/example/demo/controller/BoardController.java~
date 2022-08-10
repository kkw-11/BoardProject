package com.example.demo.controller;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j // Loggin Annotation
public class BoardController {
    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결, 객체 주입
    private ArticleRepository articleRepository;
    @GetMapping("test")
    public String hello(){
        return "hello";
    }

    @GetMapping("article/register")
    public String registerArticle(){
        return "articles/register";
    }

    @PostMapping("article/register")
    public String createArticle(ArticleForm form){
        Article article = new Article();
        article = form.toEntity();
        log.info(article.toString());
        articleRepository.save(article);

        return "redirect:/article/list";
    }


    @GetMapping("article/list")
    public String list(Model model){
        System.out.println("hello");
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("artices",articles);

        return "articles/list";
    }

}
