package problem.book.client.v10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import problem.book.client.v10.dtos.ProblemDTO;
import problem.book.client.v10.dtos.RegisterDTO;
import problem.book.client.v10.guis.MainFrame;

import javax.swing.*;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		JFrame mainFrame = new MainFrame();
	}

}
