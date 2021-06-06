package pl.infoshare.jpa.screens.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import pl.infoshare.jpa.screens.aspots.model.ASpot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
}
