package pl.infoshare.jpa.screens.aspots;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.infoshare.jpa.movies.MovieRepository;
import pl.infoshare.jpa.screens.aspots.model.ASpot;
import pl.infoshare.jpa.screens.aspots.model.ASpotRepository;

@RestController
@RequiredArgsConstructor
public class ASpotController {

    private final ASpotCreateService aSpotCreateService;
    private final ASpotUpdateService aSpotUpdateService;

    @PostMapping("/api/a-spots")
    public ASpot createASpot(@RequestBody ASpot aSpot) {
        return aSpotCreateService.createASpot(aSpot);
    }

    @PutMapping("/api/a-spots/{id}/movies/{movieId}")
    @Transactional
    public void updateASpot(@PathVariable("id") Long aSpotId,
                            @PathVariable Long movieId) {
        aSpotUpdateService.updateASpot(aSpotId, movieId);
    }
}
