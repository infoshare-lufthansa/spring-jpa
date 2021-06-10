package pl.infoshare.jpa.screens.lanes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.infoshare.jpa.positions.movies.MovieRepository;
import pl.infoshare.jpa.positions.movies.model.Movie;
import pl.infoshare.jpa.screens.lanes.model.AddMoviesToLaneRequest;
import pl.infoshare.jpa.screens.lanes.model.AddMoviesToLaneResponse;
import pl.infoshare.jpa.screens.lanes.model.LaneRepository;
import pl.infoshare.jpa.screens.model.Screen;

import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

@RestController
@RequiredArgsConstructor
public class LaneController {

    private final LaneRepository laneRepository;
    private final MovieRepository movieRepository;

    @GetMapping("/api/lanes/{id}/screen")
    @Transactional
    public Screen getScreenByLane(@PathVariable Long id) {
        return laneRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getScreen();
    }

    @PutMapping("/api/lanes/{id}/movies")
    @Transactional
    public AddMoviesToLaneResponse addMoviesToLane(@PathVariable Long id,
                                                   @RequestBody AddMoviesToLaneRequest addMoviesToLaneRequest) {
        var lane = laneRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var foundMovies = movieRepository.findAllById(addMoviesToLaneRequest.getMovieIds());

        lane.setPositions(foundMovies);

        var foundMovieIds = foundMovies.stream().map(Movie::getId).collect(Collectors.toList());
        var assignedIds = addMoviesToLaneRequest.getMovieIds().stream().filter(foundMovieIds::contains).collect(Collectors.toList());
        var notFoundIds = addMoviesToLaneRequest.getMovieIds().stream().filter(not(foundMovieIds::contains)).collect(Collectors.toList());

        return new AddMoviesToLaneResponse(assignedIds, notFoundIds);
    }

    @DeleteMapping("/api/lanes/{laneId}/movies/{movieId}")
    @Transactional
    public void deleteMovieFromLane(@PathVariable Long laneId,
                                    @PathVariable Long movieId) {
        var lane = laneRepository.findById(laneId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        lane.removeMovie(movie);
    }
}
