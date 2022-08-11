package com.example.demo.controller;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


/*
To do list
GetMapping registerArticle parameter 에러 예외 처리
GetMapping createArticle parameter 에러 예외 처리
GetMapping list findByTitleContainingOrContentContaining JPA 수정 -> 필드 별 메소드 생성하기
GetMapping deleteArticle 삭제후 뒤로가기 버튼 클릭시 삭제된 페이지가 그래돌 나오는 현상 해결
 */

@Slf4j // Loggin Annotation
@Controller
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
    public String registerArticle(Model model, @RequestParam(required = false) Long id){
        System.out.println("get register!!");
        if(id == null){
            model.addAttribute("article",new Article());// 에러 페이지, exception
        }
        else{
            Article article = articleRepository.findById(id).orElse(null);
            model.addAttribute("article", article);
        }

        return "articles/register";
    }

    @PostMapping("register")
    public String createArticle(@Valid ArticleForm articleForm,  BindingResult bindingResult, Model model){

        Article article = new Article();
        article = articleForm.toEntity();
        articleValidator.validate(articleForm ,bindingResult);
        model.addAttribute("article",article);

        if(bindingResult.hasErrors()){//클라이언트서도 체크 가능

            return "articles/register";
        }

        log.info(article.toString());
        articleRepository.save(article);

        return "redirect:/article/list";
    }


    @GetMapping("edit")
    public String createArticle(Model model, @RequestParam(required = false) Long id){
        log.info(id.toString());
        Article article = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", article);

        return "articles/edit";
    }

    @PostMapping("update")
    public String updateArticle(ArticleForm articleForm){
        log.info(articleForm.toString());
        Article article = articleForm.toEntity();
        articleRepository.save(article);
        
        return "redirect:/article/list";
    }


    @GetMapping("list")
    public String list(Model model,
                       @PageableDefault(size = 2) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText){

        Page<Article> articles = articleRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable); //필드명이 바꼈을때 오류발생가능 -> 직접 메소드작성이 좋음
        int startPage = Math.max(1, articles.getPageable().getPageNumber() - 10);
        int endPage = Math.min(articles.getTotalPages(), articles.getPageable().getPageNumber() + 10);

        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("articles",articles);

        return "articles/list";
    }

    @GetMapping("show")
    public String showArticle(Model model, @RequestParam(required = false) Long id){
        if(id == null){
            return "redirect:/article/list";
        }

        Article article = articleRepository.findById(id).orElse(null); //orThrow로도 null처리 가능
        if(article == null){
            return "redirect:/article/list";
        }
        model.addAttribute("article", article);

        return "articles/show";
    }

    @GetMapping("delete")
    public String deleteArticle(Model model, @RequestParam(required = false) Long id){
        if(id == null){
            return "redirect:/article/list";
        }

        Article article = articleRepository.findById(id).orElse(null);
        if(article == null){
            return "redirect:/article/list";
        }

        articleRepository.delete(article);
        return "redirect:/article/list";
    }




}
