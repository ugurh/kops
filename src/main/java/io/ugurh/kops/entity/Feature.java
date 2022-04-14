package io.ugurh.kops.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="FEATURES")
public class Feature {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID",length=20)
    private Integer id;

    @Column(name="NAME",length=50)
    private String name;

    @ManyToOne
    @JoinColumn(name="ITEM_ID", nullable=false)
    private Item item;

}
