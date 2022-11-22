package ru.pasha.movie.service;

import ru.pasha.movie.domain.Actor;

import java.util.List;

public interface ActorService {

    List<Actor> getAll();
    Actor getActor(Integer id);
    Actor saveActor(Actor actor);
    Actor updateActor(Integer id, Actor actor);
    Actor deleteActor(Integer id);
}
