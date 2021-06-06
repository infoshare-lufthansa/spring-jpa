package pl.infoshare.jpa.screens.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import pl.infoshare.jpa.screens.aspots.model.ASpot;
import pl.infoshare.jpa.screens.lanes.model.Lane;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Screen {

    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @OneToOne
    @JsonProperty("aspot")
    private ASpot aSpot;

    @JsonIgnore
    @OneToMany(mappedBy = "screen", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Lane> lanes;

    public void removeLane(Long laneId) {
        lanes.removeIf(l -> l.getId().equals(laneId));
    }
}
