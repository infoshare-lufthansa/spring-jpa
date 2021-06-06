package pl.infoshare.jpa.movies;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private Genre genre;
    private Duration duration;
    private LocalDate releaseDate;
    private BigDecimal score;
    private URL thumbnail;

    @AttributeOverride(name = "name", column = @Column(name = "director_name"))
    @AttributeOverride(name = "surname", column = @Column(name = "director_surname"))
    @AttributeOverride(name = "birthDate", column = @Column(name = "director_birth_date"))
    private Person director;
}
