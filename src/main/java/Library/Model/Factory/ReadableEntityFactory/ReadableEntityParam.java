package Library.Model.Factory.ReadableEntityFactory;

import java.time.LocalDateTime;

public record ReadableEntityParam(Integer id, String name, String author, LocalDateTime createdAt) {

}
