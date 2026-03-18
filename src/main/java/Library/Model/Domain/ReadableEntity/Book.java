package Library.Model.Domain.ReadableEntity;

import Library.Model.Domain.TextEntity.TextEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book implements ReadableEntity {

  private final Integer id;
  private final TextEntity name;
  private final TextEntity author;
  private final LocalDateTime createdAt;

  @Override
  public String getAuthor() {
    return author.getText();
  }

  @Override
  public String getName() {
    return name.getText();
  }
}
