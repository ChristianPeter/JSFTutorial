/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.java.jsftutorial.ticketing.entity;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author debiandev
 */
@Entity
public class TicketUser extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String email;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "TicketUser[ id=" +getId() + " ]";
    }

}
