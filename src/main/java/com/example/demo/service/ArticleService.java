package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
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
        articleRepository.save(article);

        return article.getId();

    }

    public List<Article> findArticles(){return articleRepository.findAll();}

    public Optional<Article> findOne(Long articleId){return articleRepository.findById(articleId);}

    public Page<Article> findTitleAndPage(String articleTitle, Pageable pageable){return articleRepository.findByTitleContaining(articleTitle, pageable);
    }

    public void deleteOne(Article article){return ;}

}
