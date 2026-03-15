package Library.Model.Domain.TextEntity;

public class Name extends TextEntity {

  public Name(String name) {
    super(name);
  }

  @Override
  protected void CheckText() {
    if (this.text == null || this.text.isEmpty()) {
      throw new IllegalArgumentException("Имя не может быть пустым");
    }
  }

}
