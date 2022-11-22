package ru.pasha.movie.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pasha.movie.domain.Actor;
import ru.pasha.movie.exception.exceptions.ActorNotFoundException;
import ru.pasha.movie.repository.ActorRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    public List<Actor> getAll() {
        return actorRepository.findAll();
    }

    @Override
    public Actor getActor(Integer id) {
        Optional<Actor> actor = actorRepository.findById(id);

        if (actor.isEmpty()) {
            throw new ActorNotFoundException(String.format("Actor with id %s not found", id));
        }

        return actor.get();
    }

    @Override
    @Transactional
    public Actor saveActor(Actor actor) {
        actor.setMovies(new ArrayList<>());

        return actorRepository.save(actor);
    }

    @Override
    @Transactional
    public Actor updateActor(Integer id, Actor actor) {
        Optional<Actor> existedActor = actorRepository.findById(id);

        if (existedActor.isEmpty()) {
            throw new ActorNotFoundException(String.format("Actor with id %s not found", id));
        }

        Actor actorToUpdate = existedActor.get();
        actorToUpdate.setFirstName(actor.getFirstName());
        actorToUpdate.setLastName(actor.getLastName());
        actorToUpdate.setBirthdayDate(actor.getBirthdayDate());
        actorToUpdate.setDescription(actor.getDescription());
        actorToUpdate.setMovies(actor.getMovies());

        return actorToUpdate;
    }

    @Override
    public Actor deleteActor(Integer id) {
        Optional<Actor> actor = actorRepository.findById(id);

        if (actor.isEmpty()) {
            throw new ActorNotFoundException(String.format("Actor with id %s not found", id));
        }

        actorRepository.delete(actor.get());

        return actor.get();
    }
}
