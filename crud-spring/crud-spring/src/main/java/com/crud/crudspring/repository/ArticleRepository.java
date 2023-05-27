package com.crud.crudspring.repository;

import com.crud.crudspring.domain.Article;

import java.util.List;
import java.util.Optional;


public interface ArticleRepository {
    Article save(Article article);//save 기능
    Optional<Article> findById(Long id); //id로 게시물을 찾음,null일때 optional로 감싸줌
    Optional<Article> findByContent(String content);
    Optional<Article> findByTitle(String title);
    Optional<Article> findByWriter(String writer);
    List<Article> findAll();

    //delete id 값을 받아와서 값을 db에서 삭제 ,update는 findById로 사용
    void delete(Long id);


}
