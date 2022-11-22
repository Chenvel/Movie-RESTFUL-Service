package ru.pasha.movie.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pasha.movie.domain.Movie;
import ru.pasha.movie.domain.Review;
import ru.pasha.movie.exception.exceptions.MovieNotFoundException;
import ru.pasha.movie.exception.exceptions.ReviewNotFoundException;
import ru.pasha.movie.repository.MovieRepository;
import ru.pasha.movie.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReview(Integer id) {
        Optional<Review> review = reviewRepository.findById(id);

        if (review.isEmpty()) {
            throw new ReviewNotFoundException(String.format("Review with id %s not found", id));
        }

        return review.get();
    }

    @Override
    @Transactional
    public Review saveReview(Review review) {
        review.setReplies(new ArrayList<>());
        
        Optional<Movie> movie = movieRepository.findMovieByName(review.getMovieName());

        if (movie.isEmpty()) {
            throw new MovieNotFoundException(String.format("Movie with name %s not found", review.getMovieName()));
        }

        movie.get().addReview(review);

        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public Review updateReview(Integer id, Review review) {
        Optional<Review> existedReview = reviewRepository.findById(id);

        if (existedReview.isEmpty()) {
            throw new ReviewNotFoundException(String.format("Review with id %s not found", id));
        }

        Review reviewToUpdate = existedReview.get();
        reviewToUpdate.setText(review.getText());
        reviewToUpdate.setParent(review.getParent());
        reviewToUpdate.setDate(review.getDate());
        reviewToUpdate.setScore(review.getScore());

        return reviewToUpdate;
    }

    @Override
    @Transactional
    public Review deleteReview(Integer id) {
        Optional<Review> existedReview = reviewRepository.findById(id);

        if (existedReview.isEmpty()) {
            throw new ReviewNotFoundException(String.format("Review with id %s not found", id));
        }

        reviewRepository.delete(existedReview.get());

        return existedReview.get();
    }
}
