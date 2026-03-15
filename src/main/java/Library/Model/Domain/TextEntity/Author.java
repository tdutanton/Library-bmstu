package Library.Model.Domain.TextEntity;

public class Author extends TextEntity {

  public Author(String name) {
    super(name);
  }

  @Override
  protected void CheckText() {
    if (this.text == null || this.text.isEmpty()) {
      throw new IllegalArgumentException("Поле Автора не может быть пустым");
    }
  }

}
