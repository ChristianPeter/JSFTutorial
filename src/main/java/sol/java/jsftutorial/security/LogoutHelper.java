/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.java.jsftutorial.security;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author debiandev
 */
@Named
@RequestScoped
public class LogoutHelper implements Serializable{

    public String logout(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        System.out.println("vix ext ctx " + ctx.getExternalContext().getUserPrincipal());
        final ExternalContext extCtx = ctx.getExternalContext();
        final HttpSession session = (HttpSession) extCtx.getSession(false);
        if (session != null){
            session.invalidate();
            try {
                extCtx.redirect("/JSFTutorial/login.html");
            } catch (IOException ex) {
                Logger.getLogger(LogoutHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        return "";
    }
}
