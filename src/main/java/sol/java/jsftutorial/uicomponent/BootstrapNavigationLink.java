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
@FacesComponent(createTag = true, tagName = "navlink")
public class BootstrapNavigationLink extends UIComponentBase {

    @Override
    public String getFamily() {
        return "sol.java.jsftutorial.uicomponent";
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        writer.append("<li class='active'>");
        super.encodeBegin(context); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        super.encodeEnd(context); //To change body of generated methods, choose Tools | Templates.
        final ResponseWriter writer = context.getResponseWriter();
        writer.append("</li>");
    }

}
