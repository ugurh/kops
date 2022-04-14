package io.ugurh.kops.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "PHONES")
public class Phone {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID",length=20)
    private Long id;

    @Column(name="NUMBER",length=20)
    private String number;

}
