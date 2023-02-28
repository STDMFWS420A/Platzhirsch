package hochschule;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    public void harness(HttpRequest request, Consumer<HttpResponse<String>> validate) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        validate.accept(response);
    }
    @Test
    public void testHealthCheck() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:5000"))
                .GET()
                .build();
        harness(request,response->assertEquals("Server should be up and running",404,response.statusCode()));
    }
    @Test
    public void testLoginSuccessful() throws URISyntaxException,IOException,InterruptedException{
        String username="Thorsten";
        String password = "Gruppenkacken_mit_Thomas12345";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:5000/login"))
                .POST(HttpRequest.BodyPublishers.ofString(String.format("{\"username\":%s,\"password\":%s}",username,password)))
                .build();
        harness(request,response->assertEquals("Server should be up and running",200,response.statusCode()));
    }
    @Test
    public void testLoginFailed() throws URISyntaxException,IOException,InterruptedException{
        String username="Thorsten";
        String password = "Gruppenkacken_ohne_Thomas12345";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:5000/login"))
                .POST(HttpRequest.BodyPublishers.ofString(String.format("{\"username\":%s,\"password\":%s}",username,password)))
                .build();
        harness(request,response->assertEquals("Server should be up and running",401,response.statusCode()));
    }


}
