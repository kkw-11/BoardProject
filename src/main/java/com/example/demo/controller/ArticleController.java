package com.example.demo.controller;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j // Loggin Annotation
public class ArticleController {
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


    @GetMapping("article/edit")
    public String createArticle(Model model, @RequestParam(required = false) Long id){
        log.info(id.toString());
        Article article = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", article);

        return "articles/edit";
    }

    @GetMapping("article/list")
    public String list(Model model){
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);

        return "articles/list";
    }

}
