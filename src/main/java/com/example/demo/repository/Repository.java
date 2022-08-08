package com.example.demo.repository;

import com.example.demo.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<Article, Long> {
}
