package ru.pasha.movie.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.pasha.movie.domain.Movie;
import ru.pasha.movie.domain.MovieActorRequest;
import ru.pasha.movie.service.MovieService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<Movie> getAll() {
        return movieService.getAll();
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Integer id) {
        return movieService.getMovie(id);
    }

    @PostMapping
    public Movie saveMovie(@RequestBody MovieActorRequest movie) {
        return movieService.saveMovie(movie);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Integer id, @RequestBody MovieActorRequest movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/{id}")
    public Movie deleteMovie(@PathVariable Integer id) {
        return movieService.deleteMovie(id);
    }
}
