package pl.infoshare.jpa.positions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.infoshare.jpa.positions.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

    Page<Position> findAllByTitleContaining(String title, Pageable pageable);
}
