package Library.Model.Domain.TextEntity;

/**
 * Реализация класса с текстовой сущностью для текстовых объектов-значений "Автор".
 */
public class Author extends TextEntity {

  public Author(String name) {
    super(name);
  }

  @Override
  protected void CheckText(String value) {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException("Поле Автора не может быть пустым");
    }
  }

}
