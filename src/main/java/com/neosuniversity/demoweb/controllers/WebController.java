package com.neosuniversity.demoweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String getRoot(){

        return "index";
    }
    @GetMapping("/admin")
    public String getAdmin(){

        return "admin";
    }
}
