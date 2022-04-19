package io.ugurh.kops.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MANUSCRIPT_AUTHOR")
@AssociationOverrides({
        @AssociationOverride(name = "manuscriptAuthorId.manuscript", joinColumns = @JoinColumn(name = "MANUSCRIPT_ID", referencedColumnName = "ID")),
        @AssociationOverride(name = "manuscriptAuthorId.author", joinColumns = @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID"))})
public class ManuscriptAuthor implements Serializable {
    private static final long serialVersionUID = 3182293188081684002L;

    @EmbeddedId
    private ManuscriptAuthorId manuscriptAuthorId = new ManuscriptAuthorId();

    @Column(name = "PUBLISHER", nullable = false, length = 100)
    private String publisher;

    public ManuscriptAuthorId getManuscriptAuthorId() {
        return manuscriptAuthorId;
    }

    public void setManuscriptAuthorId(ManuscriptAuthorId manuscriptAuthorId) {
        this.manuscriptAuthorId = manuscriptAuthorId;
    }

    @Transient
    public Manuscript getManuscript() {
        return getManuscriptAuthorId().getManuscript();
    }

    public void setManuscript(Manuscript manuscript) {
        getManuscriptAuthorId().setManuscript(manuscript);
    }

    @Transient
    public Author getAuthor() {
        return getManuscriptAuthorId().getAuthor();
    }

    public void setAuthor(Author author) {
        getManuscriptAuthorId().setAuthor(author);
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
