package pl.infoshare.jpa.screens.lanes.model;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.infoshare.jpa.screens.model.Screen;

public interface LaneRepository extends JpaRepository<Lane, Long> {
    void deleteAllByScreen(Screen screen);
}
