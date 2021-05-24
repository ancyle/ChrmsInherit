package user.ancyle.chrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import user.ancyle.chrms.entities.concretes.JobSeeker;

import java.util.Optional;

public interface JobSeekerRepo extends JpaRepository<JobSeeker,Short> {
    Optional<JobSeeker> findByNationalId(Long nationalId);
    Optional<JobSeeker> findByUserMail(String mail);
}
