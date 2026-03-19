package Library.Model.Domain.ReadableEntity;

import java.time.LocalDateTime;

/**
 * Базовый интерфейс, определяющий контракт для сущностей, доступных для чтения в библиотеке.
 */
public interface ReadableEntity {

  /**
   * Возвращает уникальный идентификатор сущности.
   *
   * @return ID сущности
   */
  Integer getId();

  /**
   * Возвращает название сущности (например, название книги).
   *
   * @return название
   */
  String getName();

  /**
   * Возвращает имя автора сущности.
   *
   * @return автор
   */
  String getAuthor();

  /**
   * Возвращает дату и время создания записи о сущности в системе.
   *
   * @return дата создания
   */
  LocalDateTime getCreatedAt();

}