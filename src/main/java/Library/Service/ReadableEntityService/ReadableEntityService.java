package Library.Service.ReadableEntityService;

import Library.Model.Domain.ReadableEntity.ReadableEntity;
import Library.Model.Factory.ReadableEntityFactory.ReadableEntityFactory;
import Library.Model.Factory.ReadableEntityFactory.ReadableEntityParam;
import Library.Repository.LibraryRepository.LibraryRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Сервис для реализации бизнес-логики с использованием репозитория. Для операций с читаемыми
 * сущностями и операций, в которых сущности взаимодействуют с читателями.
 */
@Service
@RequiredArgsConstructor
public class ReadableEntityService {

  private final LibraryRepository libraryRepository;
  private final ReadableEntityFactory readableEntityFactory;

  public Integer createReadableEntity(ReadableEntityParam param) {
    if (libraryRepository.readableEntityExistsByName(param.name())) {
      throw new RuntimeException("Книга с таким названием уже существует");
    }
    return libraryRepository.createReadableEntity(
        readableEntityFactory.createReadableEntity(param));
  }

  public List<ReadableEntity> getReadableEntities() {
    return libraryRepository.getReadableEntities();
  }

  public Optional<ReadableEntity> getReadableEntityByName(String name) {
    return libraryRepository.findReadableEntityByName(name);
  }

  public Integer giveREntityToReader(String readerName, String rEntityName,
      LocalDateTime borrowed, LocalDateTime dueDate) {
    return libraryRepository.giveREntityToReader(readerName, rEntityName, borrowed, dueDate);
  }

  public Integer returnREntity(String readerName, String rEntityName, LocalDateTime returned) {
    return libraryRepository.returnREntity(readerName, rEntityName, returned);
  }

  public boolean readableEntityExists(String name) {
    return libraryRepository.readableEntityExistsByName(name);
  }

  public List<ReadableEntity> getBorrowedByReader(String name) {
    return libraryRepository.getBorrowedByReader(name);
  }

  public List<String[]> getTopReadableEntities() {
    return libraryRepository.getTopReadableEntities();
  }

  public List<String[]> getBorrowedRE() {
    return libraryRepository.getBorrowedRE();
  }

}
