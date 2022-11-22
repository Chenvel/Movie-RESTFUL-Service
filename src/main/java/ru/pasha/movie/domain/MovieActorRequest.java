package ru.pasha.movie.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class MovieActorRequest {
    private Movie movie;
    private List<String> actorsNames;
}
