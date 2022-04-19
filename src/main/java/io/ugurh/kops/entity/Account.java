package io.ugurh.kops.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ACCOUNTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NUMBER", nullable = false, length = 100)
    private String number;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "accounts")
    private Set<Client> clients = new HashSet<>();

}

