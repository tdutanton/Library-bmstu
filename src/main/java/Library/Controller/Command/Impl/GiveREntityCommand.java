package Library.Controller.Command.Impl;

import Library.Controller.Command.BaseCommand;
import Library.Controller.Command.CommandContext;
import java.time.LocalDateTime;
import java.util.Optional;

public class GiveREntityCommand extends BaseCommand {

  public GiveREntityCommand(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    String bookName = ctx.getStringInput("Введите название книги для выдачи: ").toLowerCase();
    if (bookName.isEmpty()) {
      System.out.println("Название не может быть пустым");
      return;
    }
    if (!ctx.service().readableEntityExists(bookName)) {
      System.out.println("Книга не найдена");
      return;
    }
    String name = ctx.getStringInput("Введите имя читателя, которому выдается книга: ")
        .toLowerCase();
    if (name.isEmpty()) {
      System.out.println("Имя не может быть пустым");
      return;
    }
    if (!ctx.service().readerExists(name)) {
      System.out.println("Читатель не найден");
      return;
    }
    Optional<LocalDateTime> dueDateOpt = ctx.getDateInput(
        "Введите крайнюю дату возврата (дд.мм.гггг): ");
    LocalDateTime dueDate = dueDateOpt.orElse(null);
    if (dueDate != null && dueDate.isBefore(LocalDateTime.now())) {
      System.out.println("Дата возврата не может быть раньше даты выдачи");
      return;
    }
    Integer id = ctx.service().giveREntityToReader(name, bookName, dueDate);
    if (id > 0) {
      System.out.println("Книга успешно выдана. id записи = " + id);
    } else {
      System.out.println("Книга не выдана");
    }
  }
}
