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
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author debiandev
 */
@Named
@RequestScoped
public class LogoutHelper implements Serializable {

    private String username;
    private String password;

    public String logout() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        System.out.println("vix ext ctx " + ctx.getExternalContext().getUserPrincipal());
        final ExternalContext extCtx = ctx.getExternalContext();
        final HttpSession session = (HttpSession) extCtx.getSession(false);
        if (session != null) {
            session.invalidate();
            try {
                extCtx.redirect("/JSFTutorial");
            } catch (IOException ex) {
                Logger.getLogger(LogoutHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return "";
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.login(username, password);
            return "/index?faces-redirect=true";
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown login", null));
            return null;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
