package pl.infoshare.jpa.screens.lanes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import pl.infoshare.jpa.positions.model.Position;
import pl.infoshare.jpa.positions.movies.model.Movie;
import pl.infoshare.jpa.screens.model.Screen;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Lane {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ManyToOne
    @JsonIgnore
    private Screen screen;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Position> positions;

    public void setPositions(List<Movie> foundMovies) {
        positions.clear();
        positions.addAll(foundMovies);
    }

    public void removeMovie(Movie movie) {
        positions.remove(movie);
    }
}
