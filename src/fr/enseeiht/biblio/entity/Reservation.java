package fr.enseeiht.biblio.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    private Student student;

    @ManyToOne(optional = false)
    private Book book;

    @Column(nullable = false)
    private LocalDateTime reservedAt;
    
    @Column
    private boolean validated;

    // Constructors, getters and setters
    public Reservation() {
    	this.validated = false;
    }

    public Reservation(Student student, Book book) {
        this.student = student;
        this.book = book;
        this.reservedAt = LocalDateTime.now();
        this.validated = false;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LocalDateTime getReservedAt() {
		return reservedAt;
	}

	public void setReservedAt(LocalDateTime reservedAt) {
		this.reservedAt = reservedAt;
	}
	
	public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }
	
}
