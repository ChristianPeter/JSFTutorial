/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.java.jsftutorial.ticketing.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
//import org.ocpsoft.rewrite.annotation.Join;
import sol.java.jsftutorial.ticketing.boundary.TicketResource;
import sol.java.jsftutorial.ticketing.entity.Ticket;

/**
 *
 * @author debiandev
 */
@Named
@ViewScoped
//@Join(path="/tickets", to="/faces/ticket/list.xhtml")
public class TicketOverview implements Serializable {

    private static final Logger LOG = Logger.getLogger(TicketOverview.class.getName());
    
    @Inject
    private TicketResource ticketResource;

    private int page = 0;
    private int pageSize = 5;


    /*
     @Produces
     @RequestScoped
     @Named(value = "allTickets")
     public List<Ticket> getAllTickets() {
     System.out.println("getAllTickets!");
     return ticketResource.findAll();
     }
     */
    List<Ticket> allTicketsPaginated; //= new ArrayList<>();

    public List<Ticket> getAllTicketsPaginated() {
        if (allTicketsPaginated == null) {
            allTicketsPaginated = new ArrayList<>();
            initTickets();
        }
        return allTicketsPaginated;
    }

    public void setAllTicketsPaginated(List<Ticket> allTicketsPaginated) {
        this.allTicketsPaginated = allTicketsPaginated;
    }

    @PostConstruct
    public void init() {
        LOG.info("init()");
    }

    /*
     @Produces
     @RequestScoped
     @Named(value = "allTicketsPaginated")
     public List<Ticket> getAllPaginated() {
     System.out.println(" private String test = "test";getAllPaginated!");
     System.out.println(getPage());
     System.out.println(getTest());
        
     FacesContext ctx = FacesContext.getCurrentInstance();
     System.out.println(ctx.getCurrentPhaseId());
     return ticketResource.findRange(page * pageSize, pageSize);
     }
     */
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void updateList(AjaxBehaviorEvent e) {
        LOG.info(e.toString());
        initTickets();
    }

    public void next() {
        final int count = ticketResource.count();
        final double sizer = (double)count /pageSize;
        if (page+1 < sizer ){
            page++;
        }
        initTickets();
    }

    public void prev() {
        page--;
        if (page < 0) {
            page = 0;
        }
        initTickets();
    }

    private void initTickets() {
        allTicketsPaginated.clear();
        allTicketsPaginated.addAll(ticketResource.findRange(page * pageSize, pageSize));
    }

}
