package org.techfire225.webapp;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import edu.wpi.first.wpilibj.Filesystem;
import java.io.File;

public class Webserver {
    public Webserver() throws Exception {
        Server server = new Server(5801);
        
        ServletContextHandler servlets = new ServletContextHandler(ServletContextHandler.SESSIONS);
        
        servlets.addServlet(RedirectApplet.class, "/");
        servlets.addServlet(ConstantsApplets.List.class, "/constants/list");
        servlets.addServlet(ConstantsApplets.Update.class, "/constants/update");
        
        //servlets.addServlet(AutonomousApplets.AutonomousList.class, "/auto/list");
        //servlets.addServlet(AutonomousApplets.AutonomousSelect.class, "/auto/set");

        servlets.addServlet(StateSocket.class, "/state/socket");
        servlets.addServlet(StateApplets.LatestState.class, "/state/latest");
        
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });
        resource_handler.setResourceBase(new File(Filesystem.getDeployDirectory(), "www").getPath());

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resource_handler, servlets });
        server.setHandler(handlers);

        server.start();
    }
}
