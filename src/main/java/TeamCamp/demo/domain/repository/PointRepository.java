package TeamCamp.demo.domain.repository;

import TeamCamp.demo.domain.model.point.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point,Long> {
}
