package pl.infoshare.jpa.screens.lanes.model;

import lombok.Value;

import java.util.List;

@Value
public class AddMoviesToLaneResponse {
    List<Long> assignedIds;
    List<Long> notFoundIds;
}
