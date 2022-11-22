package ru.pasha.movie.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pasha.movie.domain.Actor;
import ru.pasha.movie.domain.Movie;
import ru.pasha.movie.domain.MovieActorRequest;
import ru.pasha.movie.exception.exceptions.ActorNotFoundException;
import ru.pasha.movie.exception.exceptions.MovieNotFoundException;
import ru.pasha.movie.repository.ActorRepository;
import ru.pasha.movie.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovie(Integer id) {
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isEmpty()) {
            throw new MovieNotFoundException(String.format("Movie with id %s not found", id));
        }

        return movie.get();
    }

    @Override
    @Transactional
    public Movie saveMovie(MovieActorRequest movieActorRequest) {
        Movie movie = movieActorRequest.getMovie();
        movie.setReviews(new ArrayList<>());
        addActorsToMovie(movieActorRequest, movie);

        return movieRepository.save(movie);
    }

    @Override
    @Transactional
    public Movie updateMovie(Integer id, MovieActorRequest movieActorRequest) {
        Movie movie = movieActorRequest.getMovie();
        Optional<Movie> existedMovie = movieRepository.findById(id);

        if (existedMovie.isEmpty()) {
            throw new MovieNotFoundException(String.format("Movie with id %s not found", id));
        }

        Movie movieToUpdate = existedMovie.get();
        movieToUpdate.setName(movie.getName());
        movieToUpdate.setYear(movie.getYear());
        movieToUpdate.setDescription(movie.getDescription());
        movieToUpdate.setReviews(movie.getReviews());
        addActorsToMovie(movieActorRequest, movieToUpdate);

        return movieToUpdate;
    }

    @Override
    @Transactional
    public void addActorsToMovie(MovieActorRequest movieActorRequest, Movie movie) {
        movie.setActors(new ArrayList<>());

        movieActorRequest.getActorsNames().forEach(name -> {
            Optional<Actor> actor = actorRepository.findActorByFullName(name);

            if (actor.isEmpty()) {
                throw new ActorNotFoundException(String.format("Actor with name %s not found", name));
            }

            movie.addActor(actor.get());
        });
    }

    @Override
    @Transactional
    public Movie deleteMovie(Integer id) {
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isEmpty()) {
            throw new MovieNotFoundException(String.format("Movie with id %s not found", id));
        }

        movieRepository.delete(movie.get());

        return movie.get();
    }
}
