package Library.Model.Domain.Reader;

import java.time.LocalDateTime;

/**
 * Базовый интерфейс, определяющий контракт для сущности читателя библиотеки.
 */
public interface Reader {

  /**
   * Возвращает уникальный идентификатор читателя.
   *
   * @return ID читателя
   */
  Integer getId();

  /**
   * Возвращает имя читателя.
   *
   * @return имя
   */
  String getName();

  /**
   * Возвращает электронный адрес читателя.
   *
   * @return email
   */
  String getEmail();

  /**
   * Возвращает дату и время регистрации читателя в системе.
   *
   * @return дата создания
   */
  LocalDateTime getCreatedAt();
}