package Library.Domain.TextEntity;

public class Author extends TextEntity {

  public Author(String name) {
    super(name);
  }

  @Override
  protected void CheckText() {
    if (this.name == null || this.name.isEmpty()) {
      throw new IllegalArgumentException("Поле Автора не может быть пустым");
    }
  }

}
