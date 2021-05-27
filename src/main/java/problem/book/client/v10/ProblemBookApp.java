package problem.book.client.v10;

import com.sun.tools.javac.Main;
import problem.book.client.v10.dtos.LoggedInDTO;
import problem.book.client.v10.guis.MainFrame;

/**
 * The main class of the application. This creates every GUI in the app.
 */
public class ProblemBookApp {
    private static boolean isStudent = false;
    private static LoggedInDTO loggedInDTO = null;

    public static void startApplication() {
        MainFrame mainFrame = new MainFrame();
        // Wait until the main frame closes
        while (mainFrame.isActive());


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
