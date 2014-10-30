/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sol.java.jsftutorial.uicomponent;

import java.io.IOException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author debiandev
 */
@FacesComponent(createTag = true, tagName = "row")
public class BootstrapRow extends UIComponentBase {
    
    @Override
    public String getFamily() {
        return "sol.java.jsftutorial.uicomponent";
    }
    
    
     @Override
    public void encodeBegin(FacesContext context) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        writer.append("<div class='row'>");
        super.encodeBegin(context); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        super.encodeEnd(context); //To change body of generated methods, choose Tools | Templates.
        final ResponseWriter writer = context.getResponseWriter();
        writer.append("</div>");
    }
    /*
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        String value = (String) getAttributes().get("value");
        if (value != null) {
            ResponseWriter responseWriter = context.getResponseWriter();
            responseWriter.write(value.toUpperCase());
        }
    }
    
    */
    
}
