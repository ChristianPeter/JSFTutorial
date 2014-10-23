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

    private final String[] subjects = {"Harddisk broken", "Screen is black", "Cannot start windows", "Mails are lost", "My Mouse doesn't scroll", "Key lost", "Password forgotten"};
    private final String[] descriptions = {"PC won't start. It makes strange noises.", "The screen is black and the lamp is off.", "Windows tries to start but a blue screen happens", "All my mails are gone!", "My Dog ate the key", "I've changed my password but cannot remember it."};

    @PostConstruct
    public void init() {
        System.out.println("postconstruct");
        TicketUser u1 = new TicketUser();
        u1.setEmail("dalton@test.com");
        u1.setName("Jack Dalton");

        ticketResource.saveUser(u1);

        TicketUser u2 = new TicketUser();
        u2 = new TicketUser();
        u2.setEmail("macgyver@test.com");
        u2.setName("MacGyver");

        ticketResource.saveUser(u2);

        for (int i = 0; i < 10; i++) {
            Ticket t1 = new Ticket();
            t1.setCreator((i % 2 == 0) ? u1 : u2);
            t1.setEditor((i % 2 == 1) ? u1 : u2);
            t1.setSubmittedDate(new Date());
            t1.setDescription(descriptions[i % descriptions.length]);
            t1.setSubject(subjects[i % subjects.length]);

            System.out.println("ticket " + t1);
            ticketResource.saveTicket(t1);

        }
    }
}
