package main;

import accounts.AccountServiceImpl;
import base.AccountService;
import base.Context;
import base.DBService;
import dbService.executer.DBServiceImpl;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

/**
 * Created by antonandreev on 08/04/2017.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Context context = new Context();
        context.add(DBService.class, DBServiceImpl.getInstance());
        context.add(AccountService.class, AccountServiceImpl.getInstance());

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new SignUpServlet(context)), "/signup");
        contextHandler.addServlet(new ServletHolder(new SignInServlet(context)), "/signin");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("src/com/triador/resources/templates");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {resourceHandler, contextHandler});

        Server server = new Server(8081);
        server.setHandler(handlers);

        server.join();
        server.start();
    }
}
