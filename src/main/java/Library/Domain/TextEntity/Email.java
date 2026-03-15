package Library.Domain.TextEntity;

public class Email extends TextEntity {

  public Email(String name) {
    super(name);
  }

  @Override
  protected void CheckText() {
    if (this.name == null || this.name.isEmpty()) {
      throw new IllegalArgumentException("Поле E-Mail не может быть пустым");
    }
  }

}
