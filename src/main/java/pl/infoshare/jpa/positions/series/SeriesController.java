package pl.infoshare.jpa.positions.series;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.jpa.positions.series.model.Series;

@RestController
@RequiredArgsConstructor
public class SeriesController {

    private final SeriesRepository seriesRepository;

    @GetMapping("/api/series")
    public Page<Series> getAllSeries(Pageable pageable) {
        return seriesRepository.findAll(pageable);
    }

    @PostMapping("/api/series")
    public void createSeries(@RequestBody Series series) {
        seriesRepository.save(series);
    }

    @DeleteMapping("/api/series/{id}")
    public void deleteSeries(@PathVariable Long id) {
        seriesRepository.deleteById(id);
    }

}
