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
      ctx.logger().info("Имя не может быть пустым");
      return;
    }
    String email = ctx.getStringInput("Введите email читателя: ");
    if (email.isEmpty()) {
      ctx.logger().info("email не может быть пустым");
      return;
    }
    ctx.service().createReader(name, email);
  }
}
