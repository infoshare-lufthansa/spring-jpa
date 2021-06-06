package pl.infoshare.jpa.screens.lanes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import pl.infoshare.jpa.screens.model.Screen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
}
