/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.java.jsftutorial.ticketing.boundary;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sol.java.jsftutorial.ticketing.entity.Ticket;
import sol.java.jsftutorial.ticketing.entity.TicketUser;

/**
 *
 * @author debiandev
 */
@Stateless
public class TicketResource {

    @PersistenceContext
    private EntityManager em;

    @Resource
    private SessionContext context;

    public List<Ticket> getAllTickets() {
        System.out.println("context: " + context.getCallerPrincipal().getName());
        final TypedQuery<Ticket> q = em.createQuery("select t from Ticket t", Ticket.class);

        return q.getResultList();
    }

    public void saveTicket(Ticket t) {
        em.persist(t);
    }

    public void editTicket(Ticket t) {
        em.merge(t);
    }

    public void deleteTicket(Ticket t) {
        em.remove(t);
    }

    public void saveUser(TicketUser u) {
        em.persist(u);
    }

}
