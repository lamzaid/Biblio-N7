package fr.enseeiht.biblio.entity;

import javax.persistence.*;

@Entity
@Table(name = "exemplaires")
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Boolean disponible;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    // Constructors, getters, and setters
    public Exemplaire() {
        this.disponible = true; // Default to true when created
    }

    public Exemplaire(Book book) {
        this.book = book;
        this.disponible = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
