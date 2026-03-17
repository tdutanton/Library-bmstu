package Library.Controller.Command.Impl;

import Library.Controller.Command.BaseCommand;
import Library.Controller.Command.CommandContext;

public class CreateReadableEntityCommand extends BaseCommand {

  public CreateReadableEntityCommand(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    String name = ctx.getStringInput("Введите название книги: ").toLowerCase();
    if (name.isEmpty()) {
      System.out.println("Название не может быть пустым");
      return;
    }
    String author = ctx.getStringInput("Введите имя автора: ");
    if (author.isEmpty()) {
      System.out.println("Имя автора не может быть пустым");
      return;
    }
    Integer id = ctx.service().createReadableEntity(name, author);
    System.out.println("Книга " + name + " , автор " + author + " создана. id = " + id);
  }
}
