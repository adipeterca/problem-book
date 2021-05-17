package problem.book.client.v10.guis.utilities;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import problem.book.client.v10.dtos.RegisterDTO;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Public class that queries the Problem Book Controller for information stored in the database.
 */
public class DatabaseLinker {
    private static DatabaseLinker instance = null;
    private static final RestTemplate restTemplate = new RestTemplate();

    public static DatabaseLinker getInstance() {
        if (instance == null)
            instance = new DatabaseLinker();
        return instance;
    }

    public Integer addStudent(RegisterDTO student) {
        return restTemplate.postForObject("https://problem-book-database.herokuapp.com/student/register", student, Integer.class);

    }
}
