package pl.infoshare.jpa.screens.aspots;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pl.infoshare.jpa.positions.movies.MovieRepository;
import pl.infoshare.jpa.screens.aspots.model.ASpot;
import pl.infoshare.jpa.screens.aspots.model.ASpotRepository;

@Component
@RequiredArgsConstructor
public class ASpotCreateService {

    private static final String MOVIE_NOT_FOUND_MESSAGE = "Movie with id %s doesn't exist";

    private final MovieRepository movieRepository;
    private final ASpotRepository aSpotRepository;

    public ASpot createASpot(ASpot aSpot) {
        var movie = movieRepository.findById(aSpot.getMovie().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(MOVIE_NOT_FOUND_MESSAGE, aSpot.getMovie().getId())));

        aSpot.setMovie(movie);
        return aSpotRepository.save(aSpot);
    }
}
