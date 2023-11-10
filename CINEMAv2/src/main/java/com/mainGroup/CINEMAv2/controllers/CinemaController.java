package com.mainGroup.CINEMAv2.controllers;

import com.mainGroup.CINEMAv2.model.Cinema;
import com.mainGroup.CINEMAv2.repo.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cinemas")
public class CinemaController {

    private final CinemaRepository cinemaRepository;

    @Autowired
    public CinemaController(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @GetMapping("")
    public String getCinemas(Model model) {
        Iterable<Cinema> cinemas = cinemaRepository.findAll();
        model.addAttribute("cinemas", cinemas);
        model.addAttribute("newCinema", new Cinema());
        return "cinema_list";
    }

    @PostMapping("/add")
    public String addCinema(@ModelAttribute("newCinema") Cinema cinema) {
        cinemaRepository.save(cinema);
        return "redirect:/cinemas";
    }

    @PostMapping("/delete/{id}")
    public String deleteCinema(@PathVariable("id") long id) {
        cinemaRepository.deleteById(id);
        return "redirect:/cinemas";
    }

    @PostMapping("/update/{id}")
    public String updateCinema(@PathVariable("id") long id, @RequestParam("name") String name,
                               @RequestParam("address") String address, @RequestParam("district") String district) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cinema Id: " + id));
        cinema.setName(name);
        cinema.setAddress(address);
        cinema.setDistrict(district);
        cinemaRepository.save(cinema);
        return "redirect:/cinemas";
    }
}
