package hochschule;

import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Consumer;

@RunWith(Suite.class)
@Suite.SuiteClasses({LoginTest.class})
public class MainTest extends TestCase {
    @BeforeClass
    public static void  StartServer(){
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
        System.out.println("test");
    }

}