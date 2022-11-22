package ru.pasha.movie.service;

import ru.pasha.movie.domain.Movie;
import ru.pasha.movie.domain.MovieActorRequest;

import java.util.List;

public interface MovieService {

    List<Movie> getAll();
    Movie getMovie(Integer id);
    Movie saveMovie(MovieActorRequest movieActorRequest);
    Movie updateMovie(Integer id, MovieActorRequest movieActorRequest);
    void addActorsToMovie(MovieActorRequest movieActorRequest, Movie movie);
    Movie deleteMovie(Integer id);
}
