/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sol.java.jsftutorial.ticketing.demo;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import sol.java.jsftutorial.ticketing.boundary.TicketResource;
import sol.java.jsftutorial.ticketing.entity.Ticket;
import sol.java.jsftutorial.ticketing.entity.TicketUser;

/**
 *
 * @author debiandev
 */
@Singleton
@Startup
public class TicketDemoDataCreator {
    
    @Inject
    private TicketResource ticketResource;
    
    
    @PostConstruct
    public void init(){
        TicketUser u1 = new TicketUser();
        u1.setEmail("dalton@test.com");
        u1.setName("Jack Dalton");
        
        ticketResource.saveUser(u1);
        
        TicketUser u2 = new TicketUser();
        u2 = new TicketUser();
        u2.setEmail("macgyver@test.com");
        u2.setName("MacGyver");
        
        ticketResource.saveUser(u2);
        
        
        Ticket t1 = new Ticket();
        t1.setCreator(u1);
        t1.setEditor(u2);
        t1.setSubmittedDate(new Date());
        t1.setDescription("Fix the plane. The landing gears are broken and we are gonna get out of fuel soon. Really soon. So hurry.");
        t1.setSubject("Fix the Plane!");
        
        
        ticketResource.saveTicket(t1);
        
        t1 = new Ticket();
        t1.setCreator(u1);
        t1.setEditor(u2);
        t1.setSubmittedDate(new Date());
        t1.setDescription("Fix the Reactor. It's leaking acid. Hurry, Mac!");
        t1.setSubject("Fix the Reactor!");
        
        
        ticketResource.saveTicket(t1);
        
    }
}
