package com.example.board.repository;

import com.example.board.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByTitle(String title);
    List<Article> findByTitleOrContent(String title, String content);

    Page<Article> findByTitleContainingOrContentContaining(String title, String Content, Pageable pageable);
    Page<Article> findByTitleContaining(String title, Pageable pageable);


    @Override
    List<Article> findAll();

    @Override
    void delete(Article entity);
}
