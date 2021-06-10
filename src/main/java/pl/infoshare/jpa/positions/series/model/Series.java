package pl.infoshare.jpa.positions.series.model;

import lombok.Getter;
import lombok.Setter;
import pl.infoshare.jpa.positions.model.Position;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("series")
public class Series extends Position {
    Integer numberOfEpisodes;
}
