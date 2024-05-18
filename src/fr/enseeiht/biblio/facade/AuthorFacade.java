package fr.enseeiht.biblio.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import fr.enseeiht.biblio.entity.Author;

@Stateless
public class AuthorFacade {

    @PersistenceContext(unitName = "MaPU")
    private EntityManager em;

    public List<Author> findAll() {
        return em.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

    public Author find(int id) {
        return em.find(Author.class, id);
    }

    public void create(Author author) {
        em.persist(author);
    }
}
