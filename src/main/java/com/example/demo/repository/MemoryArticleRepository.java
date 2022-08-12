package com.example.demo.repository;

import com.example.demo.entity.Article;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class MemoryArticleRepository implements ArticleRepository{

    private static Map<Long, Article> store = new HashMap<>();

    private static long sequence = 0L;

    @Override
    public Article save(Article article){
//        article.setId(++sequence);
        store.put(article.getId(), article);

        return article;
    }
    public void clearStore(){
        store.clear();
    }




    @Override
    public List<Article> findByTitle(String title) {
        return null;
    }

    @Override
    public List<Article> findByTitleOrContent(String title, String content) {
        return null;
    }

    @Override
    public Page<Article> findByTitleContainingOrContentContaining(String title, String Content, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Article> findAll() {
        return null;
    }

    @Override
    public List<Article> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Article> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Article entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Article> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Article> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Article> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Article> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Article> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Article getOne(Long aLong) {
        return null;
    }

    @Override
    public Article getById(Long aLong) {
        return null;
    }

    @Override
    public Article getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Article> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Article> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Article> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Article> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Article> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Article> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Article, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }


}

