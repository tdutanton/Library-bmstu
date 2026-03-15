package Library.Repository;

import Library.Model.Domain.ReadableEntity.ReadableEntity;
import Library.Model.Domain.Reader.Reader;
import java.util.Optional;

public interface LibraryRepository {

  void createReader(Reader reader);

  void createReadableEntity(ReadableEntity entity);

  Optional<Reader> findReaderById(Integer id);

  Optional<ReadableEntity> findReadableEntityByName(String name);
}
