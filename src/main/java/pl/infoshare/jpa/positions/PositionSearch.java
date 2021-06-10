package pl.infoshare.jpa.positions;

import lombok.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import pl.infoshare.jpa.positions.model.Genre;
import pl.infoshare.jpa.positions.model.Position;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class PositionSearch {

    String title;
    Genre genre;
    BigDecimal minScore;
    BigDecimal maxScore;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate minDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate maxDate;

    public Specification<Position> asSpecification() {
        return PositionFilters.byTitle(title)
                .and(PositionFilters.byGenre(genre))
                .and(PositionFilters.byDate(minDate, maxDate))
                .and(PositionFilters.byScore(minScore, maxScore));
    }
}
