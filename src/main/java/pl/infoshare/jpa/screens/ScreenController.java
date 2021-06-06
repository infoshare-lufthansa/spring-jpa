package pl.infoshare.jpa.screens;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.infoshare.jpa.screens.aspots.ASpotCreateService;
import pl.infoshare.jpa.screens.aspots.model.ASpotRepository;
import pl.infoshare.jpa.screens.model.Screen;
import pl.infoshare.jpa.screens.model.ScreenRepository;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ScreenController {

    private final ASpotRepository aSpotRepository;
    private final ScreenRepository screenRepository;
    private final ASpotCreateService aSpotCreateService;

    @PostMapping("/api/screens")
    @Transactional
    public Screen createScreen(@RequestBody Screen screen) {
        var aSpot = Optional.ofNullable(screen.getASpot().getId())
                .flatMap(aSpotRepository::findById)
                .orElseGet(() -> aSpotCreateService.createASpot(screen.getASpot()));

        screen.setASpot(aSpot);
        return screenRepository.save(screen);
    }

    @DeleteMapping("/api/screens/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScreen(@PathVariable Long id) {
        var screen = screenRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        screenRepository.delete(screen);
        aSpotRepository.delete(screen.getASpot());
    }
}
