package user.ancyle.chrms.entities.concretes;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="job_seekers")
@PrimaryKeyJoinColumn
public class JobSeeker extends User {
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="national_id")
    private Long nationalId;
    @Column(name="birth_year")
    private Short birthYear;

    @Column(name="user_id")
    private short userId;
}
