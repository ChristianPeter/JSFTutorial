/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.java.jsftutorial.ticketing.view;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import sol.java.jsftutorial.ticketing.boundary.TicketResource;
import sol.java.jsftutorial.ticketing.entity.Ticket;

/**
 *
 * @author debiandev
 */
@Named
@RequestScoped
public class TicketOverview {

    @Inject
    private TicketResource ticketResource;

    @Produces
    @RequestScoped
    @Named(value = "allTickets")
    public List<Ticket> getAllTickets() {
        System.out.println("GetAllTickets!");
        return ticketResource.getAllTickets();
    }

}
