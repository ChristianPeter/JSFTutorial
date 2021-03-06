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
import javax.persistence.TypedQuery;
import sol.java.jsftutorial.ticketing.entity.Ticket;
import sol.java.jsftutorial.ticketing.entity.TicketTag;
import sol.java.jsftutorial.ticketing.entity.TicketUser;

/**
 *
 * @author debiandev
 */
@Stateless
public class TicketResource {

    @PersistenceContext
    private EntityManager em;

    /*
     @Resource
     private SessionContext context;

     public List<Ticket> getAllTickets() {
     System.out.println("context: " + context.getCallerPrincipal().getName());
     final TypedQuery<Ticket> q = em.createQuery("select t from Ticket t", Ticket.class);

     return q.getResultList();
     }
     */
    public Ticket findById(String id) {
        return em.find(Ticket.class, Long.valueOf(id));
    }

    public List<Ticket> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Ticket.class));
        return em.createQuery(cq).getResultList();
    }

    public List<Ticket> findRange(int first, int maxresults) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Ticket.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(maxresults);
        q.setFirstResult(first);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Ticket> rt = cq.from(Ticket.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public void saveTicket(Ticket t) {
        em.persist(t);
    }

    public Ticket editTicket(Ticket t) {
        return em.merge(t);
    }

    public void deleteTicket(Ticket t) {
        t = em.find(Ticket.class, t.getId()); // hmmmmmm is this the best way to do that??
        em.remove(t);
    }

    public void saveUser(TicketUser u) {
        em.persist(u);
    }

    public void saveTag(TicketTag t) {
        em.persist(t);
    }

    public List<TicketTag> getAllTicketsTags() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(TicketTag.class));
        return em.createQuery(cq).getResultList();
    }

    public TicketTag findByName(String name) {
        final TypedQuery<TicketTag> query = em.createQuery("from TicketTag t where t.name = :n", TicketTag.class);
        query.setParameter("n", name);
        return query.getSingleResult();
    }

    public TicketTag findTagByUuid(String uuid) {
        final TypedQuery<TicketTag> query = em.createQuery("from TicketTag t where t.uuid = :n", TicketTag.class);
        query.setParameter("n", uuid);
        return query.getSingleResult();
    }

    public TicketTag findTagById(String id) {
        return em.find(TicketTag.class, Long.valueOf(id));
    }

}
