package io.ugurh.kops.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="ITEMS")
public class Item {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID",length=20)
    private Integer id;

    @Column(name="NAME",length=50)
    private String name;

    @OneToMany(mappedBy="item",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<Feature> features;

}
