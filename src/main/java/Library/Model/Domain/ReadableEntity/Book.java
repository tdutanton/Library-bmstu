package Library.Model.Domain.ReadableEntity;

import Library.Model.Domain.TextEntity.TextEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Конкретная реализация сущности для книг в библиотеке. Использует объекты-значения для названия и
 * автора.
 */
@RequiredArgsConstructor
@Data
public class Book implements ReadableEntity {

  /**
   * Уникальный идентификатор книги.
   */
  private final Integer id;

  /**
   * Название книги как объект-значение.
   */
  private final TextEntity name;

  /**
   * Автор книги как объект-значение.
   */
  private final TextEntity author;

  /**
   * Дата и время создания записи о книге.
   */
  private final LocalDateTime createdAt;

  /**
   * Возвращает имя автора в виде строки.
   *
   * @return текст имени автора
   */
  @Override
  public String getAuthor() {
    return author.getText();
  }

  /**
   * Возвращает название книги в виде строки.
   *
   * @return текст названия
   */
  @Override
  public String getName() {
    return name.getText();
  }

}