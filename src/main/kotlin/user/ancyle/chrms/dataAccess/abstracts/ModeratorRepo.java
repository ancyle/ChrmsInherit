package user.ancyle.chrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import user.ancyle.chrms.entities.concretes.Moderator;

public interface ModeratorRepo extends JpaRepository<Moderator,Short> {
}
