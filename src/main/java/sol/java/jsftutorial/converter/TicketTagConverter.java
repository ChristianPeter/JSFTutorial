/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.java.jsftutorial.converter;

import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import sol.java.jsftutorial.ticketing.boundary.TicketResource;
import sol.java.jsftutorial.ticketing.entity.TicketTag;

/**
 *
 * @author debiandev
 */
@FacesConverter(value = "ticketTagConverter")
public class TicketTagConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(TicketTagConverter.class.getName());

    @Inject
    TicketResource resource;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LOG.info("getAsObject " + value);
        final TicketTag found = resource.findTagByUuid(value);
        if (found != null) {
            LOG.info(found.getName());

        }
        else {
            LOG.info("null");
                    
        }
        return found;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        TicketTag tag = (TicketTag) value;
        LOG.info("getAsString" + tag.getUuid());
        return tag.getUuid();
    }

}
