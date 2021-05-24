package user.ancyle.chrms.entities.concretes;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="employers")
@PrimaryKeyJoinColumn
public class Employer extends User {

    @Column(name="corp_name")
    private String corpName;
    @Column(name="web_site")
    private String webSite;
    @Column(name="phoneNumber")
    private String phoneNumber;
}
