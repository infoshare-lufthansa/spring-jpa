package pl.infoshare.jpa.positions.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.infoshare.jpa.screens.lanes.model.Lane;

import javax.persistence.*;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Inheritance
@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
@DiscriminatorColumn(name = "position_type")
public abstract class Position {

    @Id
    @GeneratedValue
    private Long id;
    private UUID uuid = UUID.randomUUID();

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    private LocalDate releaseDate;

    private BigDecimal score;
    private URL thumbnail;

    @JsonIgnore
    @ManyToMany(mappedBy = "positions")
    private Set<Lane> lanes;
}
