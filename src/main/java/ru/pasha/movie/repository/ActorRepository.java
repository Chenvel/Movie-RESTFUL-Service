package ru.pasha.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pasha.movie.domain.Actor;

import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    Optional<Actor> findActorByFullName(String fullName);
}
