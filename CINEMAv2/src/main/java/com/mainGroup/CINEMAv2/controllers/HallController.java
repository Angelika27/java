package com.mainGroup.CINEMAv2.controllers;

import com.mainGroup.CINEMAv2.model.Hall;
import com.mainGroup.CINEMAv2.repo.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/halls")
public class HallController {

    private final HallRepository hallRepository;

    @Autowired
    public HallController(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @GetMapping("")
    public String getHalls(Model model) {
        Iterable<Hall> halls = hallRepository.findAll();
        model.addAttribute("halls", halls);
        model.addAttribute("newHall", new Hall());
        return "hall_list";
    }

    @GetMapping("/{id}")
    public String getHall(@PathVariable("id") long id, Model model) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid hall Id: " + id));
        model.addAttribute("hall", hall);
        return "hall_details";
    }

    @PostMapping("/add")
    public String addHall(@ModelAttribute("newHall") Hall hall) {
        hallRepository.save(hall);
        return "redirect:/halls";
    }

    @PostMapping("/delete/{id}")
    public String deleteHall(@PathVariable("id") long id) {
        hallRepository.deleteById(id);
        return "redirect:/halls";
    }

    @PostMapping("/update/{id}")
    public String updateHall(@PathVariable("id") long id, @ModelAttribute Hall updatedHall) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid hall Id: " + id));
        hall.setName(updatedHall.getName());
        hallRepository.save(hall);
        return "redirect:/halls";
    }
}
