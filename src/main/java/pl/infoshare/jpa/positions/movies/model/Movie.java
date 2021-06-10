package pl.infoshare.jpa.positions.movies.model;

import lombok.Getter;
import lombok.Setter;
import pl.infoshare.jpa.positions.model.Position;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.Duration;

@Entity
@Getter
@Setter
@DiscriminatorValue("movie")
public class Movie extends Position {

    private Duration duration;

    @AttributeOverride(name = "name", column = @Column(name = "director_name"))
    @AttributeOverride(name = "surname", column = @Column(name = "director_surname"))
    @AttributeOverride(name = "birthDate", column = @Column(name = "director_birth_date"))
    private Person director;
}
