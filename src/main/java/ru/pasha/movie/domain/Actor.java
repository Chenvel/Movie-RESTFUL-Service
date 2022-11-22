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
@Table(name = "Actor")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birthdayDate")
    @Type(type = "date")
    private Date birthdayDate;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private List<Movie> movies;
}
