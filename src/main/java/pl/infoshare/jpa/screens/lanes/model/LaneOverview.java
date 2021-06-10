package pl.infoshare.jpa.screens.lanes.model;

import pl.infoshare.jpa.positions.movies.model.MovieOverview;

import java.util.List;

public interface LaneOverview {
    Long getId();
    String getTitle();
    List<MovieOverview> getMovies();
}
