package user.ancyle.chrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import user.ancyle.chrms.entities.concretes.Employer;

import java.util.Optional;

public interface EmployerRepo extends JpaRepository<Employer, Short> {
    Optional<Employer> findByUserMail(String mail);
}
