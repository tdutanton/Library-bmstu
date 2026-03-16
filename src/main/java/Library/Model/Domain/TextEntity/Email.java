package Library.Model.Domain.TextEntity;

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
