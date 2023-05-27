package com.crud.crudspring.controller;

import com.crud.crudspring.domain.Article;
import com.crud.crudspring.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ArticleController {
    //service를 가져와야함
//private final ArticleService articleService = new ArticleService();
//=> 이렇게 쓰면, 여러 군데에서 articleService를 참조할 수 있음
//스프링 컨테이너에 딱 하나를 등록하고 쓰자!
    private final ArticleService articleService;
    //생성자에 autowired를 해주면,
//스프링 컨테이너에서 관리하는 articleService를 딱 연결해줌
//=> dependency injection: 의존성 주입

    @Autowired //생성자를 호출하며, spring container에 있는 articleService랑 연결해줌 => 생성자주입
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }



    @GetMapping("/articles/new") //createArticleForm 창을 띄우라는 명령
    public String createForm() {
        return "articles/createArticleForm";
    }


    //PostMapping 추가
    @PostMapping("/articles/new")
    public String create(ArticleForm form) {
        Article article = new Article(); // domain 객체 생성 후 form에서 받아온 domain 값들을 넣어줌
        article.setTitle(form.getTitle());
        article.setContent(form.getContent());
        article.setWriter(form.getWriter());
        articleService.create(article);
        return "redirect:/"; // 다시 홈 화면으로 넘겨준다
    }

    //GetMapping 추가
    @GetMapping("/articles")
    public String list(Model model) {
        List<Article> articles = articleService.findArticles();
        model.addAttribute("articles", articles);
        return "articles/articleList";
    }

    //상세 페이지
    @GetMapping("/articles/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Article> article = articleService.findOne(id);//Optional은 null이든지 아니든지 값을 넘겨줌
        model.addAttribute("article", article.orElse(null));
        return "articles/articleDetail";
    }

    //수정
    @GetMapping("/articles/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        Article article = articleService.findOne(id).get(); //optional 객체에서 꺼내오기
        model.addAttribute("article", article);
        return "articles/updateArticle";

    }

    @PostMapping("/articles/update/{id}")
    public String update(@PathVariable Long id, Article newArticle) {
        articleService.update(id, newArticle);
        return "redirect:/articles";
    }

    //삭제
    @GetMapping("/articles/delete/{id}")
    public String delete(@PathVariable Long id) {
        articleService.delete(id);
        return "redirect:/articles";
    }

}
