package Library.Controller.Command;

import Library.Service.GeneralService.GeneralService;
import Library.Utils.Logging.Logger;
import java.util.Scanner;

public record CommandContext(
    GeneralService service,
    Logger logger,
    Scanner scanner
) {

  public int getIntInput(String prompt) {
    logger.info(prompt);
    while (!scanner.hasNextInt()) {
      logger.info("Пожалуйста, введите число: ");
      scanner.next();
    }
    int value = scanner.nextInt();
    scanner.nextLine();
    return value;
  }

  public long getLongInput(String prompt) {
    logger.info(prompt);
    while (!scanner.hasNextLong()) {
      logger.info("Пожалуйста, введите корректную сумму: ");
      scanner.next();
    }
    long value = scanner.nextLong();
    scanner.nextLine();
    return value;
  }

  public String getStringInput(String prompt) {
    logger.info(prompt);
    return scanner.nextLine().trim();
  }
}