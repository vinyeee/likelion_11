package com.crud.crudspring.service;

import com.crud.crudspring.domain.Article;
import com.crud.crudspring.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
//import com.crud.crudspring.repository.MemoryArticleRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;
    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    //글 생성
    public Long create(Article article) {
        articleRepository.save(article);
        return article.getId();
    }
    //조회 (전체 글)
    public List<Article> findArticles() {
        return articleRepository.findAll();
    }
    //조회 (하나의 글 - 아이디로 찾기)
    public Optional<Article> findOne(Long articleId) {
        return articleRepository.findById(articleId);
    }

    //업데이트
    public Long update(Long id, Article article) {
        Article originalArticle = articleRepository.findById(id).get(); //Optional에서 get을 통해 꺼내줘야함.
        originalArticle.setTitle(article.getTitle());
        originalArticle.setContent(article.getContent());
        originalArticle.setWriter(article.getWriter());

        articleRepository.save(originalArticle);
        return article.getId();
    }
    //삭제
    public void delete(Long id) {
        articleRepository.delete(id);
    }


}
