package ru.pasha.movie.exception.exceptions;

public class ActorNotFoundException extends RuntimeException {

    public ActorNotFoundException(String message) {
        super(message);
    }
}
