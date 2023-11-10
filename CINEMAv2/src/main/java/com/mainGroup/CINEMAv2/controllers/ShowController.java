package com.mainGroup.CINEMAv2.controllers;

import com.mainGroup.CINEMAv2.model.Show;
import com.mainGroup.CINEMAv2.repo.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shows")
public class ShowController {

    private final ShowRepository showRepository;

    @Autowired
    public ShowController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @GetMapping("")
    public String getShows(Model model) {
        return "show_list";
    }

    @GetMapping("/{id}")
    public String getShow(@PathVariable("id") long id, Model model) {

        return "show_details";
    }

    @PostMapping("")
    public String addShow(@ModelAttribute Show show) {
        return "redirect:/shows";
    }

    @PutMapping("/{id}")
    public String updateShow(@PathVariable("id") long id, @ModelAttribute Show show) {

        return "redirect:/shows";
    }

    @DeleteMapping("/{id}")
    public String deleteShow(@PathVariable("id") long id) {

        return "redirect:/shows";
    }
}
