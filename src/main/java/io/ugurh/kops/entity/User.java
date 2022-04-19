package io.ugurh.kops.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="NAME", nullable=false, length=100)
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(	name = "USERS_GROUPS",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName="ID"),
            inverseJoinColumns = @JoinColumn(name = "GROUP_ID", referencedColumnName="ID"))
    private Set<Group> groups = new HashSet<>();
}
