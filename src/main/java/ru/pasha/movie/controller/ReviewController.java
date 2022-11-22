package ru.pasha.movie.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.pasha.movie.domain.Review;
import ru.pasha.movie.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public List<Review> getAll() {
        return reviewService.getAll();
    }

    @GetMapping("/{id}")
    public Review getReview(@PathVariable Integer id) {
        return reviewService.getReview(id);
    }

    @PostMapping
    public Review saveReview(@RequestBody Review review) {
        return reviewService.saveReview(review);
    }

    @PutMapping("/{id}")
    public Review updateReview(@PathVariable Integer id, @RequestBody Review review) {
        return reviewService.updateReview(id, review);
    }

    @DeleteMapping("/{id}")
    public Review deleteReview(@PathVariable Integer id) {
        return reviewService.deleteReview(id);
    }
}
