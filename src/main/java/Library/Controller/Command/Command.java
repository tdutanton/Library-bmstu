package Library.Controller.Command;

/**
 * Контракт для реализации команды от пользователя
 */
public interface Command {

  /**
   * Выполнить команду
   */
  void execute();

  /**
   * Имя класса команды
   *
   * @return
   */
  default String getName() {
    return getClass().getSimpleName();
  }
}