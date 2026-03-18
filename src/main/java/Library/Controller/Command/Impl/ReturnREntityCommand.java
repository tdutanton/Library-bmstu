package Library.Controller.Command.Impl;

import Library.Controller.Command.BaseCommand;
import Library.Controller.Command.CommandContext;

public class ReturnREntityCommand extends BaseCommand {

  public ReturnREntityCommand(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    String name = ctx.getStringInput("Введите имя читателя, который возвращает книгу: ")
        .toLowerCase();
    if (name.isEmpty()) {
      System.out.println("Имя не может быть пустым");
      return;
    }
    if (!ctx.service().readerExists(name)) {
      System.out.println("Читатель не найден");
      return;
    }
    String bookName = ctx.getStringInput("Введите название возвращаемой книги: ").toLowerCase();
    if (bookName.isEmpty()) {
      System.out.println("Название не может быть пустым");
      return;
    }
    if (!ctx.service().readableEntityExists(bookName)) {
      System.out.println("Книга не найдена");
      return;
    }
    Integer id = ctx.service().returnREntity(name, bookName);
    if (id > 0) {
      System.out.println("Книга успешно возвращена. id записи = " + id);
    } else {
      System.out.println("Книга не возвращена");
    }
  }
}
