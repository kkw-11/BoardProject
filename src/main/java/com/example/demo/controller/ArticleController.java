package com.example.demo.controller;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.validator.ArticleValidator;
import lombok.RequiredArgsConstructor;
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
import java.util.List;

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
            model.addAttribute("article",new Article());
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

        if(bindingResult.hasErrors()){

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
        System.out.println("hello!!!");

//        Page<Article> articles = articleRepository.findAll(pageable);
        Page<Article> articles = articleRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        System.out.println(articles.getTotalElements());
        int startPage = Math.max(1, articles.getPageable().getPageNumber() - 4);
        int endPage = Math.min(articles.getTotalPages(), articles.getPageable().getPageNumber() + 4);

        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("boards",articles);

//        List<Article> articles = articleRepository.findAll();
//        model.addAttribute("articles", articles);

        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("boards",articles);

        return "articles/list";
    }

}
