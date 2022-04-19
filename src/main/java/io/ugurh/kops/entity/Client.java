package io.ugurh.kops.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CLIENTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "CLIENT_ACCOUNT",
            joinColumns = @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID"))
    private Set<Account> accounts = new HashSet<>();

}
