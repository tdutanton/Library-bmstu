package Library.Controller.Command;

@FunctionalInterface
public interface CommandFactory {

  Command create(CommandContext ctx);
}
