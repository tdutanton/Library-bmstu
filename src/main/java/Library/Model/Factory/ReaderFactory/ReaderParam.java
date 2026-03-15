package Library.Model.Factory.ReaderFactory;

import java.time.LocalDateTime;

public record ReaderParam(Integer id, String name, String email, LocalDateTime createdAt) {

}
