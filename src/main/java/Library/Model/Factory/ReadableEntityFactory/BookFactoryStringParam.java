package Library.Model.Factory.ReadableEntityFactory;

import Library.Model.Domain.ReadableEntity.Book;
import Library.Model.Domain.ReadableEntity.ReadableEntity;
import Library.Model.Domain.TextEntity.Author;
import Library.Model.Domain.TextEntity.Name;
import org.springframework.stereotype.Component;

/**
 * Реализация фабрики читаемых сущностей с параметром
 */
@Component
public class BookFactoryStringParam implements ReadableEntityFactory {

  public ReadableEntity createReadableEntity(ReadableEntityParam param) {
    return new Book(param.id(), new Name(param.name()), new Author(param.author()),
        param.createdAt());
  }

}
