package hochschule;


import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.util.log.Log;

@WebServlet("/login")
public class Login extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try(BufferedReader reader =request.getReader() ) {
            Gson gson = new Gson();
            JsonReader jsonReader = new JsonReader(reader);
            LoginInformation loginInformation = gson.fromJson(jsonReader, LoginInformation.class);
        }
        response.setContentType("text/html;");
        response.getWriter().println("<h1>Hello world!</h1>");
    }
}
