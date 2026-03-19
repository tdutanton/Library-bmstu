package Library.Model.Domain.Reader;

import Library.Model.Domain.TextEntity.TextEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Конкретная реализация сущности читателя. Использует объекты-значения для хранения имени и email.
 */
@RequiredArgsConstructor
@Data
@Getter
public class ReaderTxtEntity implements Reader {

  /**
   * Уникальный идентификатор читателя.
   */
  private final Integer id;

  /**
   * Имя читателя как объект-значение.
   */
  private final TextEntity name;

  /**
   * Email читателя как объект-значение.
   */
  private final TextEntity email;

  /**
   * Дата и время регистрации читателя.
   */
  private final LocalDateTime createdAt;

  /**
   * Возвращает имя читателя в виде строки.
   *
   * @return текст имени
   */
  @Override
  public String getName() {
    return name.getText();
  }

  /**
   * Возвращает email читателя в виде строки.
   *
   * @return текст email
   */
  @Override
  public String getEmail() {
    return email.getText();
  }

}