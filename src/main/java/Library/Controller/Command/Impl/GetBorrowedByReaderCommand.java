package Library.Controller.Command.Impl;

import Library.Controller.Command.BaseCommand;
import Library.Controller.Command.CommandContext;
import Library.Model.Domain.ReadableEntity.ReadableEntity;
import java.util.List;

public class GetBorrowedByReaderCommand extends BaseCommand {

  public GetBorrowedByReaderCommand(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    String name = ctx.getStringInput("Введите имя читателя: ").toLowerCase();
    if (name.isEmpty()) {
      System.out.println("Имя не может быть пустым");
      return;
    }
    if (!ctx.service().readerExists(name)) {
      System.out.println("Читатель не найден");
      return;
    }
    List<ReadableEntity> list = ctx.service().getBorrowedByReader(name);
    if (list.isEmpty()) {
      System.out.println("У читателя нет книг на руках");
      return;
    }
    System.out.println("Список книг, выданных читателю " + name + " : ");
    for (ReadableEntity book : list) {
      System.out.println(book.toString());
    }
  }
}
