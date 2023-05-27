package com.crud.crudspring.controller;

import com.crud.crudspring.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import com.crud.crudspring.service.ArticleService;

@Controller
//html에서 name에 해당하는 값들이 이쪽으로 들어오게 됨
public class ArticleForm {
    private String title;
    private String content;
    private String writer;


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }



}