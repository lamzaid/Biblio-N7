package fr.enseeiht.biblio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String title;
    
    @Column
    private int publication_year;
    
   	@ManyToOne
    private Author author;

   	// Constructors, getters and setters
   	public Book() {}

	public Book(String title, int publication_year, Author author) {
		this.title = title;
		this.publication_year = publication_year;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPublication_year() {
		return publication_year;
	}

	public void setPublication_year(int publication_year) {
		this.publication_year = publication_year;
	}
	
	public Author getAuthor() {
		return this.author;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}

}
