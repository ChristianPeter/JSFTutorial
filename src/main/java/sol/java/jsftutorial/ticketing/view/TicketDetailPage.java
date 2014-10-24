/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.java.jsftutorial.ticketing.view;

import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sol.java.jsftutorial.ticketing.boundary.TicketResource;
import sol.java.jsftutorial.ticketing.entity.Ticket;

/**
 *
 * @author debiandev
 */
@Named
@ConversationScoped
public class TicketDetailPage implements Serializable {

    @Inject
    private Conversation conversation;

    @Inject
    private TicketResource ticketResource;

    private Ticket ticket = new Ticket();

    public String createTicket() {
        startConversation();
        return "/ticket/detail?faces-redirect=true";
    }

    public String editTicket(Ticket t) {
        startConversation();
        setTicket(t);
        return "/ticket/detail.xhtml?faces-redirect=true";
    }

    public String saveTicket() {
        if (ticket.getId() == null) {
            ticketResource.saveTicket(ticket);
        }
        else{
            ticketResource.editTicket(ticket);
        }
        endConversation();
        return "/ticket/list.xhtml?faces-redirect=true";
    }
    
    public String cancel(){
        endConversation();
        return "/ticket/list.xhtml?faces-redirect=true";
    }

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

}
