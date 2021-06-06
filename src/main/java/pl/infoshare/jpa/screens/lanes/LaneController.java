package pl.infoshare.jpa.screens.lanes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.infoshare.jpa.screens.lanes.model.LaneRepository;
import pl.infoshare.jpa.screens.model.Screen;

@RestController
@RequiredArgsConstructor
public class LaneController {

    private final LaneRepository laneRepository;

    @GetMapping("/api/lanes/{id}/screen")
    @Transactional
    public Screen getScreenByLane(@PathVariable Long id) {
        return laneRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getScreen();
    }
}
