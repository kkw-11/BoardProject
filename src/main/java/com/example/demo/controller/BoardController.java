package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("test")
    public String hello(){
        return "hello";
    }

    @GetMapping("article/register")
    public String registerArticle(){
        return "register";
    }

}
