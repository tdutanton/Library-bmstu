package Library.Model.Domain.Reader;

import Library.Model.Domain.TextEntity.TextEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Getter
public class ReaderTxtEntity implements Reader {

  private final Integer id;
  private final TextEntity name;
  private final TextEntity email;
  private final LocalDateTime createdAt;

  @Override
  public String getName() {
    return name.getText();
  }

  @Override
  public String getEmail() {
    return email.getText();
  }

}
