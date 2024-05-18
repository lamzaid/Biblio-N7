package fr.enseeiht.biblio.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.enseeiht.biblio.entity.Student;

@Stateless
public class StudentFacade {

    @PersistenceContext(unitName = "MaPU")
    private EntityManager em;

    public Student find(int id) {
        return em.find(Student.class, id);
    }

    public void create(Student student) {
        em.persist(student);
    }

    public void update(Student student) {
        em.merge(student);
    }

    public void delete(int id) {
        Student student = find(id);
        if (student != null) {
            em.remove(student);
        }
    }
    
    public Student findByEmailAndPassword(String email, String password) {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.email = :email AND s.password = :password", Student.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
