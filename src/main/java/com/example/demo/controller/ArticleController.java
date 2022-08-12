package com.example.demo.controller;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.ArticleService;
import com.example.demo.validator.ArticleValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.function.Supplier;


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
@RequiredArgsConstructor
public class ArticleController {
//    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결, 객체 주입, 필드주입방식 -> @RequiredArgsConstructor로 수정

    private final ArticleValidator articleValidator;

    private final ArticleService articleService;

    @GetMapping("test")
    public String hello(){
        return "index";
    }


    @GetMapping("register")
    public String registerArticle(){

        return "articles/register";
    }

    /**
     * To do list
     * 등록후 상세 페이지로 이동 필요
     *
     */
    @PostMapping("register")
    public String createArticle(Model model, ArticleForm articleForm){

        Article article = new Article();
        article = articleForm.toEntity();

        log.info(article.toString());
        articleService.register(article);

        return "redirect:/article/list";
    }


    @GetMapping("edit")
    public String createArticle(Model model, @RequestParam(required = false) Long id){
        log.info(id.toString());
//        Article article = articleRepository.findById(id).orElse(null);

        Article article = articleService.findOne(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 게시글이 없습니다.");
            }
        });

        model.addAttribute("article", article);

        return "articles/edit";
    }

    @PostMapping("update")
    public String updateArticle(ArticleForm articleForm){
        log.info(articleForm.toString());
        Article articleEntity = articleForm.toEntity();

        Article target = articleService.findOne(articleEntity.getId()).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 게시글이 없습니다.");
            }
        });

        if(target != null){
            articleService.register(articleEntity);
        }


        return "redirect:/article/list";
    }


    @GetMapping("list")
    public String list(Model model,
                       @PageableDefault(size = 2) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText){


//        Page<Article> articles = articleRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable); //필드명이 바꼈을때 오류발생가능 -> 직접 메소드작성이 좋음
        Page<Article> articles = articleService.findTitleAndPage(searchText, pageable);
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

//        Article article = articleRepository.findById(id).orElse(null); //orThrow로도 null처리 가능

        Article article = articleService.findOne(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 게시글이 없습니다.");
            }
        });
        
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

//        Article article = articleRepository.findById(id).orElse(null);
        Article article = articleService.findOne(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 페이지가 없습니다.");
            }
        });


        articleService.deleteOne(article);
        return "redirect:/article/list";
    }

}
