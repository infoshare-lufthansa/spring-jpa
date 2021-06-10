package pl.infoshare.jpa.screens.lanes.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.infoshare.jpa.screens.model.Screen;

public interface LaneRepository extends JpaRepository<Lane, Long> {

    Page<LaneOverview> findAllByScreenId(Long screenId, Pageable pageable);

    void deleteAllByScreen(Screen screen);
}
