package io.ugurh.kops.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ManuscriptAuthorId implements java.io.Serializable {

    private static final long serialVersionUID = 8395881050435165891L;

    @ManyToOne
    private Manuscript manuscript;

    @ManyToOne
    private Author author;

    public Manuscript getManuscript() {
        return manuscript;
    }
    public void setManuscript(Manuscript manuscript) {
        this.manuscript = manuscript;
    }

    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
}
