package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class ArticleRepositoryTest {
    @Autowired
    ArticleRepository articleRepository;


}
