package problem.book.client.v10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import problem.book.client.v10.guis.MainFrame;
import problem.book.client.v10.guis.StudentGui;
import problem.book.client.v10.guis.TeacherGui;

import javax.swing.*;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// ProblemBookApp.startApplication();
		// StudentGui studentGui = new StudentGui();
		TeacherGui teacherGui = new TeacherGui();
	}

}
