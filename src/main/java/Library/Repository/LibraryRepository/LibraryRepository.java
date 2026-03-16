package Library.Repository.LibraryRepository;

import Library.Model.Domain.ReadableEntity.ReadableEntity;
import Library.Model.Domain.Reader.Reader;
import java.util.Optional;

public interface LibraryRepository {

  Integer createReader(Reader reader);

  Integer createReadableEntity(ReadableEntity entity);

  Optional<Reader> findReaderById(Integer id);

  Optional<ReadableEntity> findReadableEntityByName(String name);

  boolean readerExistsById(Integer id);

  boolean readableEntityExistsById(Integer id);

  boolean readerExistsByName(String name);
}
