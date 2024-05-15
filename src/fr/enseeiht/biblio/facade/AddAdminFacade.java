package fr.enseeiht.biblio.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.enseeiht.biblio.entity.Admin;

@Stateless
public class AddAdminFacade {
	
	@PersistenceContext(unitName = "MaPU")
    private EntityManager em;

    public void addAdmin(String firstName, String lastName, String email, String password) {
        Admin admin = new Admin(firstName, lastName, email, password);
        em.persist(admin);
    }
}
