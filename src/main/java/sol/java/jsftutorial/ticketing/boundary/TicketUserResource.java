/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.java.jsftutorial.ticketing.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sol.java.jsftutorial.ticketing.entity.TicketUser;

/**
 *
 * @author debiandev
 */
@Stateless
public class TicketUserResource {

    @PersistenceContext
    private EntityManager em;

    public List<TicketUser> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(TicketUser.class));
        return em.createQuery(cq).getResultList();
    }
    
}
