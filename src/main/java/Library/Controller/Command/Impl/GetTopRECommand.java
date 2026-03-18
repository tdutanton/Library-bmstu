package Library.Controller.Command.Impl;

import Library.Controller.Command.BaseCommand;
import Library.Controller.Command.CommandContext;
import java.util.List;

public class GetTopRECommand extends BaseCommand {

  public GetTopRECommand(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    List<String[]> list = ctx.service().getTopReadableEntities();
    if (list.isEmpty()) {
      System.out.println("Книги в библиотеке отсутствуют");
      return;
    }
    System.out.println("Список популярных книг: ");
    int i = 1;
    for (String[] book : list) {
      System.out.printf("%d. %s — взята %s раз(а)\n",
          i++, book[0], book[1]);
    }
  }
}
