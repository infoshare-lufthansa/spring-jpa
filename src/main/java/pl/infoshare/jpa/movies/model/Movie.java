package pl.infoshare.jpa.movies.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.infoshare.jpa.screens.lanes.model.Lane;

import javax.persistence.*;
import java.math.BigDecimal;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
public class Movie {

    @Id
    @GeneratedValue
    private Long id;
    private UUID uuid = UUID.randomUUID();

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    private Duration duration;
    private LocalDate releaseDate;
    private BigDecimal score;
    private URL thumbnail;

    @AttributeOverride(name = "name", column = @Column(name = "director_name"))
    @AttributeOverride(name = "surname", column = @Column(name = "director_surname"))
    @AttributeOverride(name = "birthDate", column = @Column(name = "director_birth_date"))
    private Person director;

    @ManyToMany(mappedBy = "movies")
    private Set<Lane> lanes;


}
