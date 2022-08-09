package com.example.demo.controller;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.validator.ArticleValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j // Loggin Annotation
@RequestMapping("article")
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결, 객체 주입
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleValidator articleValidator;
    @GetMapping("test")
    public String hello(){
        return "hello";
    }

    @GetMapping("register")
    public String registerArticle(){
        return "articles/register";
    }

    @PostMapping("register")
    public String createArticle(@Valid ArticleForm form,  BindingResult bindingResult){
        articleValidator.validate(form ,bindingResult);
        if(bindingResult.hasErrors()){
            return "articles/register";
        }
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

    @PostMapping("article/update")
    public String updateArticle(ArticleForm articleForm){
        log.info(articleForm.toString());
        Article article = articleForm.toEntity();
        articleRepository.save(article);
        
        return "redirect:/article/list";
    }


    @GetMapping("list")
    public String list(Model model){
        System.out.println("hello!!!");
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);

        return "articles/list";
    }

}