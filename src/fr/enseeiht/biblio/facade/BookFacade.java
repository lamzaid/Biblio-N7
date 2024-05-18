package fr.enseeiht.biblio.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.enseeiht.biblio.entity.Book;

@Stateless
public class BookFacade {

    @PersistenceContext(unitName = "MaPU")
    private EntityManager em;

    public List<Book> findAll() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    public void deleteBook(int bookId) {
        Book book = em.find(Book.class, bookId);
        if (book != null) {
            em.remove(book);
        } else {
            throw new IllegalArgumentException("No book found with ID: " + bookId);
        }
    }

    public void create(Book book) {
        em.persist(book);
    }
    
    public Book find(int id) {
        return em.find(Book.class, id);
    }
    
    public void remove(Book book) {
        Book b = em.merge(book);
        em.remove(b);
    }
    
    public List<Book> findAllAvailable() {
        TypedQuery<Book> query = em.createQuery(
            "SELECT b FROM Book b WHERE b.id NOT IN (SELECT r.book.id FROM Reservation r)",
            Book.class);
        return query.getResultList();
    }
}
