package Library.Model.Domain.Reader;

import java.time.LocalDateTime;

public interface Reader {

  Integer getId();

  String getName();

  String getEmail();

  LocalDateTime getCreatedAt();
}
