package pl.infoshare.jpa.screens.lanes.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class AddMoviesToLaneRequest {

    List<Long> movieIds;

    @JsonCreator
    public AddMoviesToLaneRequest(@JsonProperty("movieIds") List<Long> movieIds) {
        this.movieIds = movieIds;
    }
}
