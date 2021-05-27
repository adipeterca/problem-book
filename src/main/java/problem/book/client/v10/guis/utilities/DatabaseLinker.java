package problem.book.client.v10.guis.utilities;

import org.springframework.web.client.RestTemplate;
import problem.book.client.v10.dtos.*;

import javax.xml.crypto.Data;


/**
 * Public class that queries the Problem Book Controller for information stored in the database.
 */
public class DatabaseLinker {
    private static DatabaseLinker instance = null;
    private static final RestTemplate restTemplate = new RestTemplate();

    private DatabaseLinker() {}

    public static DatabaseLinker getInstance() {
        if (instance == null)
            instance = new DatabaseLinker();
        return instance;
    }

    public Integer addStudent(RegisterDTO student) {
        return restTemplate.postForObject("https://problem-book-database.herokuapp.com/student/register", student, Integer.class);
    }

    public LoggedInDTO loginStudent(LoginDTO student) {
        return restTemplate.postForObject("https://problem-book-database.herokuapp.com/student/login", student, LoggedInDTO.class);
    }

    public Integer addTeacher(RegisterDTO teacher) {
        return restTemplate.postForObject("https://problem-book-database.herokuapp.com/teacher/register", teacher, Integer.class);
    }

    public LoggedInDTO loginTeacher(LoginDTO teacher) {
        return restTemplate.postForObject("https://problem-book-database.herokuapp.com/teacher/login", teacher, LoggedInDTO.class);
    }

    public AvatarDTO getAvatarForId(Integer id) {
        var response = restTemplate.getForEntity("https://problem-book-database.herokuapp.com/avatar/?id=" + id, AvatarDTO.class);
        if (response.getStatusCodeValue() == 200) {
            return response.getBody();
        }
        return null;
    }
}
