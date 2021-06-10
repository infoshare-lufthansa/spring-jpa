package pl.infoshare.jpa.screens.aspots;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import pl.infoshare.jpa.positions.movies.MovieRepository;
import pl.infoshare.jpa.screens.aspots.model.ASpotRepository;

@Component
@RequiredArgsConstructor
public class ASpotUpdateService {

    private static final String ASPOT_NOT_FOUND_MESSAGE = "A-Spot with id %s doesn't exist";
    private static final String MOVIE_NOT_FOUND_MESSAGE = "Movie with id %s doesn't exist";

    private final MovieRepository movieRepository;
    private final ASpotRepository aSpotRepository;

    public void updateASpot(@PathVariable("id") Long aSpotId,
                            @PathVariable Long movieId) {
        var aSpot = aSpotRepository.findById(aSpotId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(ASPOT_NOT_FOUND_MESSAGE, aSpotId)));
        var movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(MOVIE_NOT_FOUND_MESSAGE, movieId)));

        aSpot.setMovie(movie);
    }
}
