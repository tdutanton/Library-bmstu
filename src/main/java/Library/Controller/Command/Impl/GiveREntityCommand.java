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
    String name = ctx.getStringInput("Введите название книги для выдачи: ").toLowerCase();
    if (name.isEmpty()) {
      System.out.println("Название не может быть пустым");
      return;
    }
    String bookName = ctx.getStringInput("Введите имя читателя, которому выдается книга: ");
    if (bookName.isEmpty()) {
      System.out.println("Имя не может быть пустым");
      return;
    }

    Optional<LocalDateTime> dueDateOpt = ctx.getDateInput(
        "Введите крайнюю дату возврата (дд.мм.гггг): ");
    LocalDateTime dueDate = dueDateOpt.orElse(null);
    Integer id = ctx.service().giveREntityToReader(name, bookName, dueDate);
    if (id > 0) {
      System.out.println("Книга успешно выдана. id записи = " + id);
    } else {
      System.out.println("Книга не выдана");
    }
  }
}
