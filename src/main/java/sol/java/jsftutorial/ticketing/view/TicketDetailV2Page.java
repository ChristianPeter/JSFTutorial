/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.java.jsftutorial.ticketing.view;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.ocpsoft.rewrite.annotation.Join;
import sol.java.jsftutorial.ticketing.boundary.TicketResource;
import sol.java.jsftutorial.ticketing.entity.Ticket;

/**
 *
 * @author debiandev
 */
@Named(value = "ticketDetailPageViewscoped")
@ViewScoped
@Join(path = "/tickets/{id}", to = "/faces/ticket/detailV2.xhtml")
public class TicketDetailV2Page implements Serializable{

    private static final Logger LOG = Logger.getLogger(TicketDetailV2Page.class.getName());

    @Inject
    private TicketResource ticketResource;

    @Produces
    @Named("currentTicket")
    private Ticket ticket;

    private String id; // provided by viewParam

    public void loadTicket() {
        LOG.log(Level.INFO, "loading ticket for id {0}", id);
        if (id!= null){
        ticket = ticketResource.findById(id);
        }
        else {
            // "create new ticket"
            ticket = new Ticket();
        }
        LOG.log(Level.INFO, "found ticket {0}", ticket.getSubject());
    }
    
    public String saveTicket() {
        if (ticket.getId() == null) {
            ticketResource.saveTicket(ticket);
        }
        else{
            ticketResource.editTicket(ticket);
        }
        return "/ticket/list.xhtml?faces-redirect=true";
    }

    /* getters & setters */
    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
