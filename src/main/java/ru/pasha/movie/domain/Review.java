package ru.pasha.movie.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Review")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"replies", "parent"})
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    @JsonIgnore
    private Movie movie;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "username")
    private String username;

    @Column(name = "date")
    @Type(type = "date")
    private Date date;

    @Column(name = "text")
    private String text;

    @Column(name = "score")
    private float score;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH })
    @JsonIgnore
    private Review parent;

    @OneToMany(mappedBy = "parent")
    @Fetch(FetchMode.JOIN)
    @Column(name = "children")
    private List<Review> replies;
}
