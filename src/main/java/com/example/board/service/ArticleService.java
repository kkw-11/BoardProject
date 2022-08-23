package com.example.board.service;

import com.example.board.entity.Article;
import com.example.board.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {

        this.articleRepository = articleRepository;
    }

    public Long register(Article article){
        System.out.println("test print");
        articleRepository.save(article);
        return article.getId();

    }

    public List<Article> findArticles(){return articleRepository.findAll();}

    public Optional<Article> findOne(Long articleId){return articleRepository.findById(articleId);}

    public Page<Article> findTitleAndPage(String articleTitle, Pageable pageable){return articleRepository.findByTitleContaining(articleTitle, pageable);
    }

    public void deleteOne(Article article){return ;}

}
