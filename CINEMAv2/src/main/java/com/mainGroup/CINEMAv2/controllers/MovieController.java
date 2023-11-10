package com.mainGroup.CINEMAv2.controllers;

import com.mainGroup.CINEMAv2.model.Movie;
import com.mainGroup.CINEMAv2.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("")
    public String getMovies(Model model) {
        Iterable<Movie> movies = movieRepository.findAll();
        model.addAttribute("movies", movies);
        model.addAttribute("newMovie", new Movie());
        return "movie_list";
    }

    @PostMapping("/add")
    public String addMovie(@ModelAttribute("newMovie") Movie movie) {
        movieRepository.save(movie);
        return "redirect:/movies";
    }

    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable("id") long id) {
        movieRepository.deleteById(id);
        return "redirect:/movies";
    }

    @PostMapping("/update/{id}")
    public String updateMovie(@PathVariable("id") long id, @RequestParam("title") String title,
                              @RequestParam("genre") String genre, @RequestParam("director") String director) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie Id: " + id));
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setDirector(director);
        movieRepository.save(movie);
        return "redirect:/movies";
    }
}
