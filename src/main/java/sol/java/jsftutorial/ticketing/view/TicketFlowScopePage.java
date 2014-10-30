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
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

/**
 *
 * @author debiandev
 */
@Named("ticketFlowScoped")
@FlowScoped(value="flow1")
public class TicketFlowScopePage implements Serializable{
    private static final Logger LOG = Logger.getLogger(TicketFlowScopePage.class.getName());
    
    
    private List<String> values = new ArrayList<>();
    
    private String newValue = "Test";
    @PostConstruct
    public void init(){
        LOG.info("init");
        values.add("Start");
    }

    public List<String> getValues() {
        return values;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
    
    public void addValue(){
        LOG.info("ADD");
        getValues().add(newValue);
        LOG.info("ss" +values.size());
    }
    
    
}
