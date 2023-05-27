package com.crud.crudspring.repository;
import com.crud.crudspring.domain.Article;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaArticleRepository implements ArticleRepository{
    private final EntityManager em; //jpa는 entity manager로 동작함. jpa를 쓰려면, entity manager를 주입받아야함.
    public JpaArticleRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public Article save(Article article) {
        em.persist(article); //persist: 영구 저장하다
        return article;
    } //이렇게만 해주면 jpa가 insert query를 다 만들어서 db에 넣어주고, article에 setId까지 다해줌.
    @Override
    public Optional<Article> findById(Long id) {
        Article article = em.find(Article.class, id);
        return Optional.ofNullable(article);
    }
    @Override
    public Optional<Article> findByTitle(String title) {
        List<Article> result = em.createQuery("select a from Article a where a.title = :title",Article.class).setParameter("title", title).getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<Article> findByContent(String content) {
        List<Article> result = em.createQuery("select a from Article a where a.content = :content",Article.class)
                .setParameter("content", content)
                .getResultList();
        return result.stream().findAny();

    }
    @Override
    public Optional<Article> findByWriter(String writer) {
        List<Article> result = em.createQuery("select a from Article a where a.writer = :writer",Article.class)
                .setParameter("writer", writer)
                .getResultList();
        return result.stream().findAny();
    }
    @Override
    public List<Article> findAll() {
// List<Article> result = em.createQuery("select a from Article a", Article.class)
// .getResultList();
// return result;
//refactor + inline
        return em.createQuery("select a from Article a")
                .getResultList();
    }

    @Override
    public void delete(Long id) {
        Article article = findById(id).orElseThrow(() -> new EntityNotFoundException("Article not found"));
        //optional 객체가 null 일때, throw를 통해 예외 발생시킴
        em.remove(article);
    }
}
