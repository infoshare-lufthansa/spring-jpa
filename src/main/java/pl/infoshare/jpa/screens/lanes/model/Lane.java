package pl.infoshare.jpa.screens.lanes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import pl.infoshare.jpa.movies.model.Movie;
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
    private Set<Movie> movies;

    public void setMovies(List<Movie> foundMovies) {
        movies.clear();
        movies.addAll(foundMovies);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }
}
