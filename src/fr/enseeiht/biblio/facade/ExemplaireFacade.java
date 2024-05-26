package fr.enseeiht.biblio.facade;

import fr.enseeiht.biblio.entity.Exemplaire;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ExemplaireFacade {

    @PersistenceContext(unitName = "MaPU")
    private EntityManager em;

    public void create(Exemplaire exemplaire) {
        em.persist(exemplaire);
    }

    public Exemplaire find(int id) {
        return em.find(Exemplaire.class, id);
    }

    public void update(Exemplaire exemplaire) {
        em.merge(exemplaire);
    }

    public List<Exemplaire> findByBook(int bookId) {
        return em.createQuery("SELECT e FROM Exemplaire e WHERE e.book.id = :bookId", Exemplaire.class)
                 .setParameter("bookId", bookId)
                 .getResultList();
    }
    
    public List<Exemplaire> findAvailableByBook(int bookId) {
        return em.createQuery("SELECT e FROM Exemplaire e WHERE e.book.id = :bookId AND e.disponible = true", Exemplaire.class)
                 .setParameter("bookId", bookId)
                 .getResultList();
    }
}
