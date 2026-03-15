package Library.Model.Domain.ReadableEntity;

import java.time.LocalDateTime;

public interface ReadableEntity {

  Integer getId();

  String getName();

  String getAuthor();

  LocalDateTime getCreatedAt();

}
