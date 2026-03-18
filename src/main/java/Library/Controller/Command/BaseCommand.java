package Library.Controller.Command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseCommand implements Command {

  protected final CommandContext ctx;

  protected boolean confirm(String message) {
    System.out.println(message);
    String confirm = ctx.scanner().nextLine().trim().toLowerCase();
    return confirm.equals("да") || confirm.equals("yes") || confirm.equals("y");
  }
}