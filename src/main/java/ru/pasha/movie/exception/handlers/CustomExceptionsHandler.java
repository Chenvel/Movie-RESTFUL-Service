package ru.pasha.movie.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.pasha.movie.exception.exceptions.ActorNotFoundException;
import ru.pasha.movie.exception.exceptions.MovieNotFoundException;
import ru.pasha.movie.exception.exceptions.ReviewNotFoundException;
import ru.pasha.movie.exception.responses.ExceptionResponse;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ActorNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleActorNotFoundException(ActorNotFoundException e) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage(), new Date(), HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MovieNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleMovieNotFoundException(MovieNotFoundException e) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage(), new Date(), HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ReviewNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleReviewNotFoundException(ReviewNotFoundException e) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage(), new Date(), HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
