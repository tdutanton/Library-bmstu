package Library.Controller;

import Library.Controller.Command.Command;
import Library.Controller.Command.CommandContext;
import Library.Controller.Command.CommandFactory;
import Library.Controller.Command.Impl.CreateReadableEntityCommand;
import Library.Controller.Command.Impl.CreateReaderCommand;
import Library.Controller.Command.Impl.FindReadableEntity;
import Library.Controller.Command.Impl.GetReadableEntities;
import Library.Controller.Command.Impl.GetReadersCommand;
import Library.Controller.Command.Impl.GiveREntityCommand;
import Library.Service.GeneralService.GeneralService;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service
public class Controller {

  private final GeneralService service;
  private final Scanner scanner;
  private final Map<Integer, CommandFactory> menuCommands;

  public Controller(GeneralService service, Scanner scanner) {
    this.service = service;
    this.scanner = scanner;
    this.menuCommands = createCommandFactories();
  }

  private Map<Integer, CommandFactory> createCommandFactories() {
    Map<Integer, CommandFactory> map = new HashMap<>();

    map.put(1, CreateReaderCommand::new);
    map.put(2, GetReadersCommand::new);
    map.put(3, CreateReadableEntityCommand::new);
    map.put(4, GetReadableEntities::new);
    map.put(5, FindReadableEntity::new);
    map.put(6, GiveREntityCommand::new);
//    map.put(7, ShowCategoriesCommand::new);
//    map.put(8, ShowReportCommand::new);
//    map.put(9, ShowGroupsCommand::new);

    return map;
  }

  public void showMenu() {
    System.out.println("--- МЕНЮ ---");
    System.out.println("Выберите действие: ");
    System.out.println("1.	Создать Читателя");
    System.out.println("2.	Посмотреть список читателей");
    System.out.println("3.	Создать книгу");
    System.out.println("4.	Посмотреть список книг");
    System.out.println("5.	Поиск книги по названию");
    System.out.println("6.	Выдать книгу читателю");
    System.out.println("7.	Показать категории");
    System.out.println("8.	Аналитика: Подсчет разницы доходов и расходов за выбранный период");
    System.out.println("9.	Аналитика: Группировка доходов и расходов по категориям");
    System.out.println("10.	Импорт / экспорт");
    System.out.println("0.	Выход");
  }

  public void runMenu() {
    CommandContext ctx = new CommandContext(service, scanner);
    boolean shouldExit = false;

    while (!shouldExit) {
      showMenu();
      int choice = ctx.getIntInput("");

      if (choice == 0) {
        System.out.println("Выход из библиотеки.");
        shouldExit = true;
      } else {
        CommandFactory factory = menuCommands.get(choice);
        if (factory != null) {
          Command command = factory.create(ctx);
          command.execute();
        } else {
          System.out.println("Неверный выбор. Попробуйте снова.");
        }
      }
    }
  }
}