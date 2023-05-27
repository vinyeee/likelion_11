package com.example.helloSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //controller 선언
public class HelloSpringController {
    @GetMapping("hello-spring")//반환할 주소
    public String helloStatic(Model model,@RequestParam(name="str") String str){
        model.addAttribute("data",str);//data 라는 이름으로 spring! 이라는 값을 전달

        return "hello-spring";
    }
}
