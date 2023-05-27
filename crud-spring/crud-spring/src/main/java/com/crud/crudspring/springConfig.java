package com.crud.crudspring;

import com.crud.crudspring.repository.ArticleRepository;
import com.crud.crudspring.repository.JpaArticleRepository;
import com.crud.crudspring.service.ArticleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class springConfig {
    private EntityManager em;


    public springConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public ArticleService articleService() {
        return new ArticleService(articleRepository());
    }
    @Bean
    public ArticleRepository articleRepository() {
        return new JpaArticleRepository(em); //Jpa는 entity manager를 받아야함
    }


}
