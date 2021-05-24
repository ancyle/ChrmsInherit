package user.ancyle.chrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import user.ancyle.chrms.entities.concretes.User;

import java.util.Optional;
@Transactional
@Repository
public interface UserRepo extends JpaRepository<User,Short> {
    Optional<User> findByUserMail(String mail);
    boolean existsByUserMail(String mail);
    Optional<User> findUserByUserConfirmation_Code(String code);
}
