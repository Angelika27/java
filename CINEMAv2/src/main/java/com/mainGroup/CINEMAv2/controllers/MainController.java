package com.mainGroup.CINEMAv2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/hall-list")
    public String hallList(Model model) {
        return "hall_list";
    }

    @GetMapping("/movie-list")
    public String movieList(Model model) {
        return "movie_list";
    }
}
