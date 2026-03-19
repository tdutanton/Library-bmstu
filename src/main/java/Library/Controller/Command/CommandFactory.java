package Library.Controller.Command;

/**
 * Контракт фабрики команд для их унифицированного создания
 */
@FunctionalInterface
public interface CommandFactory {

  /**
   * Создать команду
   *
   * @param ctx контекст (пользовательский класс)
   * @return
   */
  Command create(CommandContext ctx);
}
