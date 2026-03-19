package Library.Model.Factory.ReaderFactory;

import java.time.LocalDateTime;

/**
 * Параметры для создания читателя
 *
 * @param id
 * @param name
 * @param email
 * @param createdAt
 */
public record ReaderParam(Integer id, String name, String email, LocalDateTime createdAt) {

  public static ReaderParam forNew(String name, String email) {
    return new ReaderParam(null, name, email, LocalDateTime.now());
  }
}
