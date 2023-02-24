package hochschule;

import junit.framework.TestCase;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Consumer;

public class MainTest extends TestCase {
    public void harness(HttpRequest request, Consumer<HttpResponse<String>> validate) throws URISyntaxException, IOException, InterruptedException {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    Main.main(new String[]{});
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        validate.accept(response);
    }
    public void testHealthCheck() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://localhost:5000"))
                .GET()
                .build();
        harness(request,response->assertEquals("Server should be up and running",200,response.statusCode()));
    }
    public void testLogin() throws URISyntaxException,IOException,InterruptedException{
        String username="Thorsten";
        String password = "Gruppenkacken_mit_Thomas12345";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://localhost:5000/login"))
                .POST(HttpRequest.BodyPublishers.ofString(String.format("{\"username\":%s,\"password\":%s}",username,password)))
                .build();
        harness(request,response->assertEquals("Server should be up and running",200,response.statusCode()));
    }

}