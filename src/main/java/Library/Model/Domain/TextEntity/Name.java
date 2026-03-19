package Library.Model.Domain.TextEntity;

/**
 * Реализация класса с текстовой сущностью для текстовых объектов-значений "Имя".
 */
public class Name extends TextEntity {

  public Name(String name) {
    super(name);
  }

  @Override
  protected void CheckText(String value) {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException("Имя не может быть пустым");
    }
  }

}
