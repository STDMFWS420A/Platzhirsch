package hochschule;
import java.net.URISyntaxException;
import java.net.URL;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {
    public static void main(String[]args) throws Exception {
        // Create a server that listens on port 8080.
        Server server = new Server(5000);
        WebAppContext webAppContext = new WebAppContext();
        server.setHandler(webAppContext);

        // Load static content from the top level directory.
        URL webAppDir = Main.class.getClassLoader().getResource(".");
        webAppContext.setResourceBase(webAppDir.toURI().toString());

        // Start the server! 🚀
        server.start();
        System.out.println("Server started!");

        // Keep the main thread alive while the server is running.
        server.join();

    }
}
