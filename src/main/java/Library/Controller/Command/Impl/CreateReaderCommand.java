package Library.Controller.Command.Impl;

import Library.Controller.Command.BaseCommand;
import Library.Controller.Command.CommandContext;

public class CreateReaderCommand extends BaseCommand {

  public CreateReaderCommand(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    String name = ctx.getStringInput("Введите имя читателя: ").toLowerCase();
    if (name.isEmpty()) {
      System.out.println("Имя не может быть пустым");
      return;
    }
    String email = ctx.getStringInput("Введите email читателя: ");
    if (email.isEmpty()) {
      System.out.println("email не может быть пустым");
      return;
    }
    Integer id = ctx.service().createReader(name, email);
    System.out.println("Читатель " + name + " создан. id = " + id);
  }
}
