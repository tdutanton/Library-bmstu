package Library.Controller.Command.Impl;

import Library.Controller.Command.BaseCommand;
import Library.Controller.Command.CommandContext;
import Library.Model.Domain.Reader.Reader;
import java.util.List;

public class GetReadersCommand extends BaseCommand {

  public GetReadersCommand(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    List<Reader> readerList = ctx.service().getReaders();
    if (readerList.isEmpty()) {
      System.out.println("Читатели в библиотеке отсутствуют");
      return;
    }
    System.out.println("Список читателей: ");
    for (Reader reader : readerList) {
      System.out.println(reader.toString());
    }
  }
}
