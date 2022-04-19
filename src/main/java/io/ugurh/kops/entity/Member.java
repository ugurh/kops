package io.ugurh.kops.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="MEMBERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="NAME", nullable=false, length=100)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "FRIENDS",
            joinColumns = @JoinColumn(name = "MEMBER1_ID", referencedColumnName="ID"),
            inverseJoinColumns = @JoinColumn(name = "MEMBER2_ID", referencedColumnName="ID"))
    private Set<Member> friends = new HashSet<>();

}

