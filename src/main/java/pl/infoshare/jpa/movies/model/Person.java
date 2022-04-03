package pl.infoshare.jpa.movies.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Data
@Embeddable
public class Person {
    private String name;
    private String surname;
    private LocalDate birthDate;
}
