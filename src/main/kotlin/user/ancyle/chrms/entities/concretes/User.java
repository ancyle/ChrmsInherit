package user.ancyle.chrms.entities.concretes;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private short userId;
    @Column(name="user_no")
    private String userNo;
    @Column(name="user_mail")
    private String userMail;
    @Column(name="user_pass")
    private String userPass;
    @Column(name="user_type")
    private String userType;
    @Column(name="created_date")
    private LocalDate createdDate= LocalDate.now();
    @Column(name="is_active")
    private boolean isActive;
}
