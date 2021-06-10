package pl.infoshare.jpa.positions;

import org.springframework.data.jpa.domain.Specification;
import pl.infoshare.jpa.positions.model.Genre;
import pl.infoshare.jpa.positions.model.Position;
import pl.infoshare.jpa.positions.model.Position_;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class PositionFilters {

    public static Specification<Position> byTitle(String title) {
        if (Objects.isNull(title)) {
            return ignore();
        }

        return (root, query, cb) -> cb.like(cb.upper(root.get(Position_.title)), "%" + title.toUpperCase() + "%");
    }

    public static Specification<Position> byGenre(Genre genre) {
        if (Objects.isNull(genre)) {
            return ignore();
        }

        return (root, query, cb) -> cb.equal(root.get(Position_.genre), genre);
    }

    public static Specification<Position> byScore(BigDecimal minScore, BigDecimal maxScore) {
        if (Objects.isNull(minScore) && Objects.isNull(maxScore)) {
            return ignore();
        }
        if (Objects.isNull(minScore)) {
            return byMaxScore(maxScore);
        }
        if (Objects.isNull(maxScore)) {
            return byMinScore(minScore);
        }

        return byMinScore(minScore).and(byMaxScore(maxScore));
    }

    public static Specification<Position> byDate(LocalDate minDate, LocalDate maxDate) {
        if (Objects.isNull(minDate) && Objects.isNull(maxDate)) {
            return ignore();
        }
        if (Objects.isNull(minDate)) {
            return byMaxDate(maxDate);
        }
        if (Objects.isNull(maxDate)) {
            return byMinDate(minDate);
        }

        return byMinDate(minDate).and(byMaxDate(maxDate));
    }

    private static Specification<Position> byMinDate(LocalDate minDate) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Position_.releaseDate), minDate);
    }

    private static Specification<Position> byMaxDate(LocalDate maxDate) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(Position_.releaseDate), maxDate);
    }

    private static Specification<Position> byMinScore(BigDecimal minScore) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Position_.score), minScore);
    }

    private static Specification<Position> byMaxScore(BigDecimal maxScore) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(Position_.score), maxScore);
    }

    private static Specification<Position> ignore() {
        return (root, query, cb) -> cb.and();
    }
}
