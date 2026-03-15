package Library.Domain.TextEntity;

import lombok.Getter;

public abstract class TextEntity {

  @Getter
  protected final String name;

  public TextEntity(String name) {
    CheckText();
    this.name = name;
  }

  protected abstract void CheckText();
}
