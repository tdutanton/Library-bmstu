package Library.Controller;

import Library.Controller.Command.Command;
import Library.Controller.Command.CommandContext;
import Library.Controller.Command.CommandFactory;
import Library.Controller.Command.Impl.CreateReaderCommand;
import Library.Service.GeneralService.GeneralService;
import Library.Utils.Logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service
public class Controller {

  private final Logger logger;
  private final GeneralService service;
  private final Scanner scanner;
  private final Map<Integer, CommandFactory> menuCommands;

  public Controller(Logger logger, GeneralService service, Scanner scanner) {
    this.logger = logger;
    this.service = service;
    this.scanner = scanner;
    this.menuCommands = createCommandFactories();
  }

  private Map<Integer, CommandFactory> createCommandFactories() {
    Map<Integer, CommandFactory> map = new HashMap<>();

    map.put(1, CreateReaderCommand::new);
//    map.put(2, CreateCategoryCommand::new);
//    map.put(3, DepositCommand::new);
//    map.put(4, WithdrawCommand::new);
//    map.put(5, ShowAccountsCommand::new);
//    map.put(6, ShowOperationsCommand::new);
//    map.put(7, ShowCategoriesCommand::new);
//    map.put(8, ShowReportCommand::new);
//    map.put(9, ShowGroupsCommand::new);

    return map;
  }

  public void showMenu() {
    System.out.println("--- МЕНЮ ---");
    System.out.println("Выберите действие: ");
    System.out.println("1.	Создать Читателя");
    System.out.println("2.	Создать категорию");
    System.out.println("3.	Пополнить");
    System.out.println("4.	Потратить");
    System.out.println("5.	Показать счета");
    System.out.println("6.	Показать операции");
    System.out.println("7.	Показать категории");
    System.out.println("8.	Аналитика: Подсчет разницы доходов и расходов за выбранный период");
    System.out.println("9.	Аналитика: Группировка доходов и расходов по категориям");
    System.out.println("10.	Импорт / экспорт");
    System.out.println("0.	Выход");
  }

  public void runMenu() {
    CommandContext ctx = new CommandContext(service, logger, scanner);
    boolean shouldExit = false;

    while (!shouldExit) {
      showMenu();
      int choice = ctx.getIntInput("");

      if (choice == 0) {
        logger.info("Выход из библиотеки.");
        shouldExit = true;
      } else {
        CommandFactory factory = menuCommands.get(choice);
        if (factory != null) {
          Command command = factory.create(ctx);
          command.execute();
        } else {
          logger.info("Неверный выбор. Попробуйте снова.\n");
        }
      }
    }
  }
}