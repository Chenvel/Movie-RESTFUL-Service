package ru.pasha.movie.service;

import ru.pasha.movie.domain.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAll();
    Review getReview(Integer id);
    Review saveReview(Review review);
    Review updateReview(Integer id, Review review);
    Review deleteReview(Integer id);
}
