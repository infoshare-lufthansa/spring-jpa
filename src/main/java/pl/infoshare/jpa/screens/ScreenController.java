package pl.infoshare.jpa.screens;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.infoshare.jpa.screens.aspots.ASpotCreateService;
import pl.infoshare.jpa.screens.aspots.model.ASpotRepository;
import pl.infoshare.jpa.screens.lanes.model.Lane;
import pl.infoshare.jpa.screens.lanes.model.LaneOverview;
import pl.infoshare.jpa.screens.lanes.model.LaneRepository;
import pl.infoshare.jpa.screens.model.Screen;
import pl.infoshare.jpa.screens.model.ScreenRepository;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ScreenController {

    private final LaneRepository laneRepository;
    private final ASpotRepository aSpotRepository;
    private final ScreenRepository screenRepository;
    private final ASpotCreateService aSpotCreateService;

    @GetMapping("/api/screens/{id}/lanes")
    public Page<LaneOverview> getLanes(@PathVariable("id") Long screenId, Pageable pageable) {
        return laneRepository.findAllByScreenId(screenId, pageable);
    }

    @PostMapping("/api/screens")
    @Transactional
    public Screen createScreen(@RequestBody Screen screen) {
        var aSpot = Optional.ofNullable(screen.getASpot().getId())
                .flatMap(aSpotRepository::findById)
                .orElseGet(() -> aSpotCreateService.createASpot(screen.getASpot()));

        screen.setASpot(aSpot);
        return screenRepository.save(screen);
    }

    @PostMapping("/api/screens/{id}/lanes")
    @Transactional
    public Lane createLaneInScreen(@PathVariable Long id,
                                     @RequestBody Lane lane) {
        var screen = screenRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        lane.setScreen(screen);
        return laneRepository.save(lane);
    }

    @DeleteMapping("/api/screens/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScreen(@PathVariable Long id) {
        var screen = screenRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        laneRepository.deleteAllByScreen(screen);
        screenRepository.delete(screen);
        aSpotRepository.delete(screen.getASpot());
    }

    @DeleteMapping("/api/screens/{id}/lanes/{laneId}")
    @Transactional
    public void deleteLane(@PathVariable("id") Long screenId,
                           @PathVariable Long laneId) {
        var screen = screenRepository.findById(screenId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        screen.removeLane(laneId);
    }
}
