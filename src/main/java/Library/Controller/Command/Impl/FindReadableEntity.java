package Library.Controller.Command.Impl;

import Library.Controller.Command.BaseCommand;
import Library.Controller.Command.CommandContext;
import Library.Model.Domain.ReadableEntity.ReadableEntity;
import java.util.Optional;

public class FindReadableEntity extends BaseCommand {

  public FindReadableEntity(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    String name = ctx.getStringInput("Введите название книги: ").toLowerCase();
    if (name.isEmpty()) {
      System.out.println("Название не может быть пустым");
      return;
    }

    Optional<ReadableEntity> result = ctx.service().getReadableEntityByName(name);
    result.ifPresentOrElse(
        book -> {
          System.out.println("Результат поиска: " + book.getName());
          System.out.println(book.toString());
        },
        () -> System.out.println("Читатель не найден")
    );
  }
}
