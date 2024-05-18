package fr.enseeiht.biblio.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import fr.enseeiht.biblio.entity.Reservation;
import java.util.List;

@Stateless
public class ReservationFacade {

    @PersistenceContext(unitName = "MaPU")
    private EntityManager em;

    public Reservation find(int id) {
        return em.find(Reservation.class, id);
    }

    public void update(Reservation reservation) {
        em.merge(reservation);
    }

    public void create(Reservation reservation) {
        em.persist(reservation);
    }

    public void cancel(int id) {
        Reservation reservation = find(id);
        if (reservation != null) {
            em.remove(reservation);
        }
    }

    public List<Reservation> findByStudent(int studentId) {
        return em.createQuery("SELECT r FROM Reservation r WHERE r.student.id = :studentId", Reservation.class)
                 .setParameter("studentId", studentId)
                 .getResultList();
    }

    public List<Reservation> findAll() {
        return em.createQuery("SELECT r FROM Reservation r", Reservation.class).getResultList();
    }
    
    public void returnBook(int reservationId) {
        Reservation reservation = find(reservationId);
        if (reservation != null) {
            em.remove(reservation);
        }
    }
}
