package user.ancyle.chrms.entities.concretes;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="po_id")
    private short poId;

    @Column(name="po_name")
    private String poName;
}
