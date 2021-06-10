package pl.infoshare.jpa.positions;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.infoshare.jpa.positions.model.Position;

@RestController
@RequiredArgsConstructor
public class PositionController {

    private final PositionRepository positionRepository;

    @GetMapping("/api/positions")
    public Page<Position> getPositions(PositionSearch search, Pageable pageable) {
        return positionRepository.findAll(search.asSpecification(), pageable);
    }
}
