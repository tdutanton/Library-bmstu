package Library;

import Library.Controller.Controller;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryApp {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(LibraryApp.class,
        args);

    Controller controller = context.getBean(Controller.class);
    controller.runMenu();
  }

  @Bean
  public Scanner scanner() {
    return new Scanner(System.in);
  }

}