package hochschule;

import junit.framework.TestCase;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MainTest extends TestCase {

    public void testMain() throws URISyntaxException, IOException, InterruptedException {
        Thread t = new Thread(new Runnable() {
            public void run() {
                Main.main(new String[]{});
            }
        });
        t.start();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:5000"))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals("Server should be up and running",200,response.statusCode());
    }
}