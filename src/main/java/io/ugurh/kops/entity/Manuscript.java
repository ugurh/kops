package io.ugurh.kops.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="MANUSCRIPTS")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Manuscript {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Integer id;

    @Column(name="NAME", nullable=false, length=100)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manuscriptAuthorId.manuscript", cascade=CascadeType.ALL)
    private Set<ManuscriptAuthor> manuscriptAuthors = new HashSet<>(0);

}
