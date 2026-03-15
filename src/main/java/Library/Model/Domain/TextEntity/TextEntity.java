package Library.Model.Domain.TextEntity;

import lombok.Getter;

public abstract class TextEntity {

  @Getter
  protected final String text;

  public TextEntity(String name) {
    CheckText();
    this.text = name;
  }

  protected abstract void CheckText();
}
