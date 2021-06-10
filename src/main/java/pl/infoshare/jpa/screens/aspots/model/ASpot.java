package pl.infoshare.jpa.screens.aspots.model;

import lombok.Getter;
import lombok.Setter;
import pl.infoshare.jpa.positions.movies.model.Movie;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ASpot {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private ASpotSize size;

    @OneToOne
    private Movie movie;
}
