package pl.infoshare.jpa.positions.series;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.infoshare.jpa.positions.series.model.Series;

public interface SeriesRepository extends JpaRepository<Series, Long> {
}
