package Library.Model.Domain.TextEntity;

import lombok.Getter;

/**
 * Абстрактный базовый класс для текстовых объектов-значений. Обеспечивает хранение строки и
 * валидацию при создании.
 */
public abstract class TextEntity {

  /**
   * Текстовое значение объекта.
   */
  @Getter
  protected final String text;

  /**
   * Создаёт объект-значение с валидацией входных данных.
   *
   * @param text строковое значение для обёртки
   */
  public TextEntity(String text) {
    CheckText(text);
    this.text = text;
  }

  /**
   * Выполняет валидацию текстового значения. Реализуется в конкретных классах-наследниках.
   *
   * @param value значение для проверки
   */
  protected abstract void CheckText(String value);
}