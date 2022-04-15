package io.ugurh.kops.entity;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "FEATURES")
public class Feature {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID",length=20)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="ITEM_ID", nullable=false)
    private Item item;

}
