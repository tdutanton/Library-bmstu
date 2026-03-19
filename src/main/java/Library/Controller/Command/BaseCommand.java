package Library.Controller.Command;

import lombok.RequiredArgsConstructor;

/**
 * Абстрактный класс команды, которую может запросить пользователь. Все дочерние классы команд
 * созданы от этого класса.
 */
@RequiredArgsConstructor
public abstract class BaseCommand implements Command {

  /**
   * Контекст (для доступа к сервису и сканеру)
   */
  protected final CommandContext ctx;
}