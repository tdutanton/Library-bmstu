package Library.Domain.TextEntity;

public class Name extends TextEntity {

  public Name(String name) {
    super(name);
  }

  @Override
  protected void CheckText() {
    if (this.name == null || this.name.isEmpty()) {
      throw new IllegalArgumentException("Имя не может быть пустым");
    }
  }

}
