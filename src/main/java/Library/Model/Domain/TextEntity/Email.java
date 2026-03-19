package Library.Model.Domain.TextEntity;

/**
 * Реализация класса с текстовой сущностью для текстовых объектов-значений "Email".
 */
public class Email extends TextEntity {

  public Email(String name) {
    super(name);
  }

  @Override
  protected void CheckText(String value) {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException("Поле E-Mail не может быть пустым");
    }
  }

}
