package user.ancyle.chrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import user.ancyle.chrms.entities.concretes.Position;

import java.util.Optional;

@Transactional
@Repository
public interface PositionRepo extends JpaRepository<Position,Short> {
    Optional<Position> findByPoName(String name);
}
