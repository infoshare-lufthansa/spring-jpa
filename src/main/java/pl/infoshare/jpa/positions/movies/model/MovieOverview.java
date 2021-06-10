package pl.infoshare.jpa.positions.movies.model;

import pl.infoshare.jpa.positions.model.Genre;

import java.net.URL;

public interface MovieOverview {
    Long getId();
    String getTitle();
    Genre getGenre();
    URL getThumbnail();
}
