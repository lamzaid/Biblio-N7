package fr.enseeiht.biblio.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.enseeiht.biblio.entity.Admin;
import fr.enseeiht.biblio.entity.Student;

@Stateless
public class LoginFacade {
	
	@PersistenceContext(unitName = "MaPU")
    private EntityManager em;
	
	public boolean isAdmin(String email, String password) {
		return checkCredentials(Admin.class, email, password);
	}
	
	public boolean isStudent(String email, String password) {
		return checkCredentials(Student.class, email, password);
	}
	
	private <T> boolean checkCredentials(Class<T> type, String email, String password) {
        try {
            TypedQuery<T> query = em.createQuery(
                "SELECT u FROM " + type.getSimpleName() + " u WHERE u.email = :email AND u.password = :password", type);
            query.setParameter("email", email);
            query.setParameter("password", password);
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
