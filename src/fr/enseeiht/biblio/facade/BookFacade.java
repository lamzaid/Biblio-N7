package fr.enseeiht.biblio.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import fr.enseeiht.biblio.entity.Book;

@Stateless
public class BookFacade {

    @PersistenceContext(unitName = "MaPU")
    private EntityManager em;

    public List<Book> findAll() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
}
