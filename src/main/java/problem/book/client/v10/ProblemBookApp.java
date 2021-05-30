package problem.book.client.v10;

import com.sun.tools.javac.Main;
import problem.book.client.v10.dtos.LoggedInDTO;
import problem.book.client.v10.guis.MainFrame;
import problem.book.client.v10.guis.StudentGui;
import problem.book.client.v10.guis.TeacherGui;

/**
 * The main class of the application. This creates every GUI in the app.
 */
public class ProblemBookApp {
    private static boolean isStudent = false;
    private static LoggedInDTO loggedInDTO = null;

    // TESTING
    // private static LoggedInDTO loggedInDTO = new LoggedInDTO("name", "adrian.peterca@yahoo.com", 1);

    public static void startApplication() {
        MainFrame mainFrame = new MainFrame();
        // Wait until the main frame closes
        while (mainFrame.stillRunning() || loggedInDTO == null);

        if (isStudent) {
            StudentGui studentGui = new StudentGui();
            while (studentGui.isActive());
        }
        else {
            TeacherGui teacherGui = new TeacherGui();
            while (teacherGui.isActive());
        }
    }

    public static boolean isIsStudent() {
        return isStudent;
    }

    public static LoggedInDTO getLoggedInDTO() {
        return loggedInDTO;
    }

    public static void setLoggedInDTO(LoggedInDTO loggedInDTO) {
        ProblemBookApp.loggedInDTO = loggedInDTO;
    }

    public static void setIsStudent(boolean isStudent) {
        ProblemBookApp.isStudent = isStudent;
    }
}
