package user.ancyle.chrms.entities.concretes;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="moderators")
public class Moderator extends User{
    //@Id
    @PrimaryKeyJoinColumn
    @Column(name="mod_id")
    private short modId;
    @Column(name="mod_full_name")
    private String modFullName;
    @Column(name="mod_key")
    private String modKey;
}
