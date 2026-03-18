package Library.Controller.Command.Impl;

import Library.Controller.Command.BaseCommand;
import Library.Controller.Command.CommandContext;
import java.util.List;

public class GetBorrowedRECommmand extends BaseCommand {

  public GetBorrowedRECommmand(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    List<String[]> list = ctx.service().getBorrowedRE();
    if (list.isEmpty()) {
      System.out.println("Не возвращенные книги в библиотеке отсутствуют");
      return;
    }
    System.out.println("Список выданных и не возвращенных книг: ");
    int i = 1;
    for (String[] book : list) {
      System.out.printf("%d. %s — находится у читателя %s\n",
          i++, book[0], book[1]);
    }
  }
}
