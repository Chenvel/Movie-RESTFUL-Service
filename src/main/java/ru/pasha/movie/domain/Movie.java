package ru.pasha.movie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Movie")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = "actors")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private int year;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "movie")
    @Fetch(FetchMode.JOIN)
    private List<Review> reviews;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    private List<Actor> actors;

    public void addActor(Actor actor) {
        actors.add(actor);
        actor.getMovies().add(this);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setMovie(this);
    }
}
