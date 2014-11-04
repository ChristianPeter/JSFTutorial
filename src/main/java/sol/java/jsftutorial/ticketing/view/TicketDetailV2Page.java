/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.java.jsftutorial.ticketing.view;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sol.java.jsftutorial.ticketing.boundary.TicketResource;
import sol.java.jsftutorial.ticketing.boundary.TicketUserResource;
import sol.java.jsftutorial.ticketing.entity.Ticket;
import sol.java.jsftutorial.ticketing.entity.TicketTag;
import sol.java.jsftutorial.ticketing.entity.TicketUser;

/**
 *
 * @author debiandev
 */
@Named(value = "ticketDetailPageViewscoped")
@ViewScoped
//@Join(path = "/tickets/{id}", to = "/faces/ticket/detailV2.xhtml")
public class TicketDetailV2Page implements Serializable {

    private static final Logger LOG = Logger.getLogger(TicketDetailV2Page.class.getName());

    @Inject
    private TicketResource ticketResource;
    
    @Inject
    private TicketUserResource userResource;

    @Produces
    @Named("currentTicket")
    private Ticket ticket;

    private String id; // provided by viewParam

    private String newTag;

    private List<TicketTag> allTicketTags;

    @PostConstruct
    public void init() {
        initAllTicketTags();
    }

    public void initAllTicketTags() {
        if (allTicketTags == null) {
            allTicketTags = ticketResource.getAllTicketsTags();
        } else {
            allTicketTags.clear();
            allTicketTags.addAll(ticketResource.getAllTicketsTags());
        }
    }
    @Produces
//    @RequestScoped
    @ViewScoped
    @Named(value = "allUsers")
    public List<TicketUser> getAllUsers(){
        LOG.info("init all Users List");
        return userResource.findAll();
    }
    
    public List<TicketTag> getAllTicketsTags() {
        return allTicketTags;
    }

    public void loadTicket() {
        LOG.log(Level.INFO, "loading ticket for id {0}", id);
        if (id != null) {
            ticket = ticketResource.findById(id);
            LOG.info("TEST:" + ticket.getTags().size());
        } else {
            // "create new ticket"
            ticket = new Ticket();
        }
        LOG.log(Level.INFO, "found ticket {0}", ticket.getSubject());
    }

    public String saveTicket() {
        if (ticket.getId() == null) {
            ticketResource.saveTicket(ticket);
        } else {
            ticketResource.editTicket(ticket);
        }
        return "/ticket/listV2.xhtml?faces-redirect=true";
    }

    public String addNewTag() {
        LOG.info("new tag: " + newTag);
        TicketTag t = new TicketTag();
        t.setName(newTag);
        t.getTickets().add(ticket);
        ticket.getTags().add(t);

        allTicketTags.add(t);
        //ticketResource.saveTag(t);
        //initAllTicketTags();

        newTag = "";
        return null;
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

    public String getNewTag() {
        return newTag;
    }

    public void setNewTag(String newTag) {
        this.newTag = newTag;
    }

}
