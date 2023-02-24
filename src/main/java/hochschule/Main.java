package hochschule;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.*;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {
    public static void main(String[]args) throws Exception {
        // Create a server that listens on port 8080.
        Server server = new Server(5000);
        WebAppContext webAppContext = new WebAppContext();
        server.setHandler(webAppContext);

        // Load static content from the resources directory.
        URL webAppDir = Main.class.getClassLoader().getResource("META-INF/resources");
        webAppContext.setResourceBase(webAppDir.toURI().toString());

        // Look for annotations in the classes directory (dev server) and in the
        // jar file (live server).
        webAppContext.setAttribute(
                "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                ".*/target/classes/|.*\\.jar");
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.setContextPath("/");

        server.setHandler(context);


        context.addServlet(new ServletHolder(new Login()), "/login");
        server.start();
        System.out.println("Server started!");

        // Keep the main thread alive while the server is running.
        server.join();

    }
}
