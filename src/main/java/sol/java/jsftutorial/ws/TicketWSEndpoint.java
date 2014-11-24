/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.java.jsftutorial.ws;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author debiandev
 */
@ServerEndpoint("/ticketstatus")
//@Singleton
//@Startup
@Stateless
public class TicketWSEndpoint {

    private static final Logger LOG = Logger.getLogger(TicketWSEndpoint.class.getName());

    private static final Set<Session> sessions
            = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) throws IOException {
        LOG.info("onOpen");
        sessions.add(session);
        session.getBasicRemote().sendText("onOpen");
    }

    @OnMessage
    public void echo(final Session session, String message) throws IOException {
        LOG.info("OnMessage");
        for (Session s : sessions) {
            if (s.isOpen()) {
                s.getBasicRemote().sendText("hello " + message);
            }
        }
//        return message + " (from your server)";
    }

    @OnError
    public void onError(Throwable t) {
        LOG.info("OnError");
        t.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        LOG.info("OnClose");
        sessions.remove(session);
    }

    @PostConstruct
    public void init() {
        LOG.info("WS init");
    }

    
    
    public void handleTicketEvent(@Observes TicketEvent event) throws IOException{
        LOG.info("handleTicketEvent " + event);
        for (Session s : sessions) {
            if (s.isOpen()) {
                s.getBasicRemote().sendText("hello " + event.payload);
            }
        }
    }
}
