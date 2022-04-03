package pl.infoshare.jpa.movies.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue
    private Long id;
    private UUID uuid = UUID.randomUUID();

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    private LocalDate releaseDate;

    private BigDecimal score;
    private URL thumbnail;
    private Duration duration;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "director_name"))
    @AttributeOverride(name = "surname", column = @Column(name = "director_surname"))
    @AttributeOverride(name = "birthDate", column = @Column(name = "director_birth_date"))
    private Person director;
}
