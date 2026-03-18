package Library.Model.Factory.ReadableEntityFactory;

import java.time.LocalDateTime;

public record ReadableEntityParam(Integer id, String name, String author, LocalDateTime createdAt) {

  public static ReadableEntityParam forNew(String name, String author) {
    return new ReadableEntityParam(null, name, author, LocalDateTime.now());
  }
}
