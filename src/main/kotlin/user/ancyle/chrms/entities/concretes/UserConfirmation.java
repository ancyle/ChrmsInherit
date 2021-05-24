package user.ancyle.chrms.entities.concretes;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="user_confirmations")
public class UserConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="uc_id")
    private short uc_id;

    @Column(name="code")
    private String code;

    @Column(name="mail_verify")
    private boolean mailVerify;

    @Column(name="mod_verify")
    private boolean modVerify;
}
