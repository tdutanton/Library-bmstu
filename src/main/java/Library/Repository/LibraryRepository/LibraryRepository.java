package Library.Repository.LibraryRepository;

import Library.Model.Domain.ReadableEntity.ReadableEntity;
import Library.Model.Domain.Reader.Reader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LibraryRepository {

  Integer createReader(Reader reader);

  Integer createReadableEntity(ReadableEntity entity);

  Optional<Reader> findReaderById(Integer id);

  Optional<ReadableEntity> findReadableEntityByName(String name);

  boolean readerExistsById(Integer id);

  boolean readableEntityExistsById(Integer id);

  boolean readerExistsByName(String name);

  List<Reader> getReaders();

  boolean readableEntityExistsByName(String name);

  List<ReadableEntity> getReadableEntities();

  Integer giveREntityToReader(String readerName, String rEntityName,
      LocalDateTime borrowed, LocalDateTime dueDate);

  Integer returnREntity(String readerName, String rEntityName,
      LocalDateTime returned);

  List<ReadableEntity> getBorrowedByReader(String name);

  List<String[]> getTopReadableEntities();

  List<String[]> getBorrowedRE();
}
