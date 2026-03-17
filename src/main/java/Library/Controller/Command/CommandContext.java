package Library.Controller.Command;

import Library.Service.GeneralService.GeneralService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;

public record CommandContext(
    GeneralService service,
    Scanner scanner
) {

  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  public int getIntInput(String prompt) {
    System.out.println(prompt);
    while (!scanner.hasNextInt()) {
      System.out.println("Пожалуйста, введите число: ");
      scanner.next();
    }
    int value = scanner.nextInt();
    scanner.nextLine();
    return value;
  }

  public long getLongInput(String prompt) {
    System.out.println(prompt);
    while (!scanner.hasNextLong()) {
      System.out.println("Пожалуйста, введите корректную сумму: ");
      scanner.next();
    }
    long value = scanner.nextLong();
    scanner.nextLine();
    return value;
  }

  public String getStringInput(String prompt) {
    System.out.println(prompt);
    return scanner.nextLine().trim();
  }

  public Optional<LocalDateTime> getDateInput(String prompt) {
    System.out.print(prompt);
    System.out.println("(формат: дд.мм.гггг или Enter для пропуска)");
    while (true) {
      String input = scanner.nextLine().trim();
      if (input.isEmpty()) {
        return Optional.empty();
      }
      try {
        LocalDate date = LocalDate.parse(input, DATE_FORMATTER);
        return Optional.of(date.atStartOfDay());
      } catch (DateTimeParseException e) {
        System.out.print(
            "Неверный формат. Пожалуйста, введите дату в формате дд.мм.гггг или Enter для пропуска: ");
      }
    }
  }
}