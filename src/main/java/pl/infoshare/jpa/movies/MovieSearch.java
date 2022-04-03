package pl.infoshare.jpa.movies;

import lombok.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import pl.infoshare.jpa.movies.model.Genre;
import pl.infoshare.jpa.movies.model.Movie;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class MovieSearch {

    String title;
    Genre genre;
    BigDecimal minScore;
    BigDecimal maxScore;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate minDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate maxDate;

    public Specification<Movie> asSpecification() {
        return MovieFilters.byTitle(title)
                .and(MovieFilters.byGenre(genre))
                .and(MovieFilters.byDate(minDate, maxDate))
                .and(MovieFilters.byScore(minScore, maxScore));
    }
}
