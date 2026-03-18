package Library.Service.GeneralService;

import Library.Model.Domain.ReadableEntity.ReadableEntity;
import Library.Model.Domain.Reader.Reader;
import Library.Model.Factory.ReadableEntityFactory.ReadableEntityParam;
import Library.Model.Factory.ReaderFactory.ReaderParam;
import Library.Service.ReadableEntityService.ReadableEntityService;
import Library.Service.ReaderService.ReaderService;
import Library.Utils.Logging.Logger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralService {

  private final ReaderService readerService;
  private final ReadableEntityService readableEntityService;
  private final Logger logger;

  public Integer createReader(String name, String email) {
    ReaderParam param = ReaderParam.forNew(name, email);
    Integer id = readerService.createReader(param);
    logger.info("Читатель создан, id = " + id);
    return id;
  }

  public List<Reader> getReaders() {
    logger.info("Вывод списка читателей");
    return readerService.getReaders();
  }

  public Integer createReadableEntity(String name, String author) {
    ReadableEntityParam param = ReadableEntityParam.forNew(name, author);
    Integer id = readableEntityService.createReadableEntity(param);
    logger.info("Книга создана, id = " + id);
    return id;
  }

  public List<ReadableEntity> getReadableEntities() {
    logger.info("Вывод списка книг");
    return readableEntityService.getReadableEntities();
  }

  public Optional<ReadableEntity> getReadableEntityByName(String name) {
    logger.info("Поиск книги по названию");
    return readableEntityService.getReadableEntityByName(name);
  }

  public Integer giveREntityToReader(String readerName, String rEntityName, LocalDateTime dueDate) {
    logger.info("Выдача книги читателю");
    return readableEntityService.giveREntityToReader(readerName, rEntityName, LocalDateTime.now(),
        dueDate);
  }

  public Integer returnREntity(String readerName, String rEntityName) {
    logger.info("Возврат книги");
    return readableEntityService.returnREntity(readerName, rEntityName, LocalDateTime.now());
  }

  public boolean readerExists(String name) {
    logger.info("Проверка на присутствие читателя в репозитории");
    return readerService.readerExists(name);
  }

  public boolean readableEntityExists(String name) {
    logger.info("Проверка на присутствие книги в репозитории");
    return readableEntityService.readableEntityExists(name);
  }

  public List<ReadableEntity> getBorrowedByReader(String name) {
    return readableEntityService.getBorrowedByReader(name);
  }

  public List<String[]> getTopReadableEntities() {
    logger.info("Получить популярные книги");
    return readableEntityService.getTopReadableEntities();
  }

  public List<String[]> getBorrowedRE() {
    logger.info("Получить список выданных книг");
    return readableEntityService.getBorrowedRE();
  }

}
