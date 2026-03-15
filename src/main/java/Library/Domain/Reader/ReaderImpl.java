package Library.Domain.Reader;

import Library.Domain.TextEntity.TextEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ReaderImpl implements Reader {

  private final Integer id;
  private final TextEntity Name;
  private final TextEntity email;
  private final LocalDateTime createdAt;

  public String getName() {
    return Name.getName();
  }

}
