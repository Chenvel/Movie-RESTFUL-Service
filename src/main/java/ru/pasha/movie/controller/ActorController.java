package ru.pasha.movie.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pasha.movie.domain.Actor;
import ru.pasha.movie.service.ActorService;
import ru.pasha.movie.service.ActorServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/actors")
@AllArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public ResponseEntity<List<Actor>> getAll() {
        return new ResponseEntity<>(actorService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActor(@PathVariable Integer id) {
        return new ResponseEntity<>(actorService.getActor(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Actor> saveActor(@RequestBody Actor actor) {
        return new ResponseEntity<>(actorService.saveActor(actor), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable Integer id, @RequestBody Actor actor) {
        return new ResponseEntity<>(actorService.updateActor(id, actor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Actor> deleteActor(@PathVariable Integer id) {
        return new ResponseEntity<>(actorService.deleteActor(id), HttpStatus.OK);
    }
}
