package Library.Model.Domain.TextEntity;

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
