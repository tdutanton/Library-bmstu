package Library.Controller.Command.Impl;

import Library.Controller.Command.BaseCommand;
import Library.Controller.Command.CommandContext;
import Library.Model.Domain.ReadableEntity.ReadableEntity;
import java.util.List;

public class GetReadableEntities extends BaseCommand {

  public GetReadableEntities(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    List<ReadableEntity> list = ctx.service().getReadableEntities();
    if (list.isEmpty()) {
      System.out.println("Книги в библиотеке отсутствуют");
      return;
    }
    System.out.println("Список книг: ");
    for (ReadableEntity book : list) {
      System.out.println(book.toString());
    }
  }
}
