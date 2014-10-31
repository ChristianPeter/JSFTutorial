/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.java.jsftutorial.ticketing.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author debiandev
 */
@Entity
public class Ticket extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String subject;

    private String description;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date submittedDate;

    @Enumerated
    private TicketStatus ticketStatus = TicketStatus.DRAFT;

    @ManyToOne(fetch = FetchType.LAZY)
    private TicketUser creator;

    @ManyToOne(fetch = FetchType.LAZY)
    private TicketUser editor;

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//    @JoinTable(name = "RL_TICKET_TAG", joinColumns = {
//        @JoinColumn(name = "TICKET_ID")}, inverseJoinColumns = {
//        @JoinColumn(name = "TAG_ID")})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<TicketTag> tags = new HashSet<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public TicketUser getCreator() {
        return creator;
    }

    public void setCreator(TicketUser creator) {
        this.creator = creator;
    }

    public TicketUser getEditor() {
        return editor;
    }

    public void setEditor(TicketUser editor) {
        this.editor = editor;
    }

    public Set<TicketTag> getTags() {
        return tags;
    }

    public void setTags(Set<TicketTag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Ticket[ id=" + getId() + " ]";
    }

}
