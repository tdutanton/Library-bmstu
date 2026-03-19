package Library.Repository.LibraryRepository;

import Library.Model.Domain.ReadableEntity.ReadableEntity;
import Library.Model.Domain.Reader.Reader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Контракт репозитория библиотеки для возможности использования разных СУБД или способов хранения
 */
public interface LibraryRepository {

  /**
   * Создать читателя
   *
   * @param reader читатель
   * @return id читателя
   */
  Integer createReader(Reader reader);

  /**
   * Создать читаемую сущность
   *
   * @param entity сущность (книга etc.)
   * @return id сущности
   */
  Integer createReadableEntity(ReadableEntity entity);

  /**
   * Поиск читателя по id
   *
   * @param id
   * @return читатель или null
   */
  Optional<Reader> findReaderById(Integer id);

  /**
   * Поиск читаемой сущности по названию
   *
   * @param name название
   * @return сущность или null
   */
  Optional<ReadableEntity> findReadableEntityByName(String name);

  /**
   * Проверка на наличие читателя в библиотеке
   *
   * @param id
   * @return true - есть читатель, false - нет
   */
  boolean readerExistsById(Integer id);

  /**
   * Проверка на наличие "книги" в библиотеке
   *
   * @param id
   * @return true - есть "книга" или false - нет
   */
  boolean readableEntityExistsById(Integer id);

  /**
   * Проверка на наличие читателя в библиотеке по его имени
   *
   * @param name
   * @return
   */
  boolean readerExistsByName(String name);

  /**
   * Получить список читателей
   *
   * @return список читателей
   */
  List<Reader> getReaders();

  /**
   * Проверка на наличие "книги" в библиотеке по названию
   *
   * @param name название
   * @return
   */
  boolean readableEntityExistsByName(String name);

  /**
   * Получить список читаемых сущностей
   *
   * @return список "книг"
   */
  List<ReadableEntity> getReadableEntities();

  /**
   * Выдать книгу читателю
   *
   * @param readerName  имя читателя
   * @param rEntityName название книги
   * @param borrowed    дата выдачи
   * @param dueDate     крайняя дата возврата
   * @return id записи в таблице в БД (0 - не удалось)
   */
  Integer giveREntityToReader(String readerName, String rEntityName,
      LocalDateTime borrowed, LocalDateTime dueDate);

  /**
   * Вернуть книгу в библиотеку
   *
   * @param readerName  имя читателя
   * @param rEntityName название "книги"
   * @param returned    дата возврата
   * @return
   */
  Integer returnREntity(String readerName, String rEntityName,
      LocalDateTime returned);

  /**
   * Получить список "книг" у читателя
   *
   * @param name имя читателя
   * @return список "книг"
   */
  List<ReadableEntity> getBorrowedByReader(String name);

  /**
   * Получить информацию о популярных книгах (по количеству выдачи)
   *
   * @return массив строк с названием книги и кколичество выдачи
   */
  List<String[]> getTopReadableEntities();

  /**
   * Получить список книг, находящихся на руках
   *
   * @return массив строк с названием книг и именами читателей
   */
  List<String[]> getBorrowedRE();
}
