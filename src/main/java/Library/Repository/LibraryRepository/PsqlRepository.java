package Library.Repository.LibraryRepository;

import Library.Model.Domain.ReadableEntity.ReadableEntity;
import Library.Model.Domain.Reader.Reader;
import Library.Model.Factory.ReadableEntityFactory.ReadableEntityFactory;
import Library.Model.Factory.ReadableEntityFactory.ReadableEntityParam;
import Library.Model.Factory.ReaderFactory.ReaderFactory;
import Library.Model.Factory.ReaderFactory.ReaderParam;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Реализация контракта библиотеки, использует PostgresQL для хранения данных
 */
@RequiredArgsConstructor
@Repository
public class PsqlRepository implements LibraryRepository {

  private final ReaderFactory readerFactory;
  private final ReadableEntityFactory readableEntityFactory;
  private final JdbcTemplate jdbcTemplate;

  private RowMapper<ReadableEntity> bookRowMapper() {
    return (ResultSet rs, int rowNum) -> {
      Integer id = rs.getInt("id");
      String name = rs.getString("name");
      String author = rs.getString("author");
      LocalDateTime dt =
          rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime()
              : null;

      ReadableEntityParam param = new ReadableEntityParam(id, name, author, dt);
      return readableEntityFactory.createReadableEntity(param);
    };
  }

  private RowMapper<Reader> readerRowMapper() {
    return (ResultSet rs, int rowNum) -> {
      Integer id = rs.getInt("id");
      String name = rs.getString("name");
      String email = rs.getString("email");
      LocalDateTime dt =
          rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime()
              : null;

      ReaderParam param = new ReaderParam(id, name, email, dt);
      return readerFactory.createReader(param);
    };
  }

  @Override
  public boolean readerExistsById(Integer id) {
    if (id == null) {
      return false;
    }
    String sql = "SELECT 1 FROM readers WHERE id = ? LIMIT 1";
    try {
      jdbcTemplate.queryForObject(sql, Integer.class, id);
      return true;
    } catch (EmptyResultDataAccessException e) {
      return false;
    }
  }

  @Override
  public boolean readerExistsByName(String name) {
    if (name == null) {
      return false;
    }
    String sql = "SELECT 1 FROM readers WHERE LOWER(name) = LOWER(?) LIMIT 1";
    try {
      jdbcTemplate.queryForObject(sql, String.class, name);
      return true;
    } catch (EmptyResultDataAccessException e) {
      return false;
    }
  }

  @Override
  public boolean readableEntityExistsById(Integer id) {
    if (id == null) {
      return false;
    }
    String sql = "SELECT 1 FROM books WHERE id = ? LIMIT 1";
    try {
      jdbcTemplate.queryForObject(sql, Integer.class, id);
      return true;
    } catch (EmptyResultDataAccessException e) {
      return false;
    }
  }

  @Override
  public boolean readableEntityExistsByName(String name) {
    if (name == null) {
      return false;
    }
    String sql = "SELECT 1 FROM books WHERE LOWER(name) = LOWER(?) LIMIT 1";
    try {
      jdbcTemplate.queryForObject(sql, String.class, name);
      return true;
    } catch (EmptyResultDataAccessException e) {
      return false;
    }
  }

  @Override
  public Integer createReader(Reader reader) {
    String sql = "INSERT INTO readers (name, email, created_at) VALUES (?, ?, ?) RETURNING id";

    return jdbcTemplate.queryForObject(sql, Integer.class, reader.getName(), reader.getEmail(),
        reader.getCreatedAt());
  }

  @Override
  public Integer createReadableEntity(ReadableEntity entity) {
    String sql = "INSERT INTO books (name, author, created_at) VALUES (?, ?, ?) RETURNING id";

    return jdbcTemplate.queryForObject(sql, Integer.class, entity.getName(), entity.getAuthor(),
        entity.getCreatedAt());
  }

  @Override
  public Integer giveREntityToReader(String readerName, String rEntityName, LocalDateTime borrowed,
      LocalDateTime dueDate) {
    String sql = """
        INSERT INTO borrowings br (reader_id, book_id, borrowed_at, due_date)
        SELECT r.id, b.id, ?, ?
        FROM readers r
        JOIN books b ON LOWER(b.name) = LOWER(?)
        WHERE LOWER(r.name) = LOWER(?)
        RETURNING br.id
        """;
    try {
      return jdbcTemplate.queryForObject(sql, Integer.class, borrowed, dueDate, rEntityName,
          readerName);
    } catch (EmptyResultDataAccessException e) {
      boolean readerExists = readerExistsByName(readerName);
      boolean bookExists = readableEntityExistsByName(rEntityName);
      if (!readerExists && !bookExists) {
        throw new IllegalArgumentException(
            "Не найдены ни читатель '" + readerName + "', ни книга '" + rEntityName + "'");
      } else if (!readerExists) {
        throw new IllegalArgumentException("Читатель не найден: " + readerName);
      } else {
        throw new IllegalArgumentException("Книга не найдена: " + rEntityName);
      }
    }
  }

  @Override
  public Integer returnREntity(String readerName, String rEntityName, LocalDateTime returned) {
    String sql = """
        UPDATE borrowings br
        SET returned_at = ?
        FROM readers r, books b
        WHERE br.reader_id = r.id
          AND br.book_id = b.id
          AND br.returned_at IS NULL
          AND LOWER(r.name) = LOWER(?)
          AND LOWER(b.name) = LOWER(?)
        RETURNING br.id
        """;
    try {
      return jdbcTemplate.queryForObject(sql, Integer.class, returned, readerName, rEntityName);
    } catch (EmptyResultDataAccessException e) {
      boolean readerExists = readerExistsByName(readerName);
      boolean bookExists = readableEntityExistsByName(rEntityName);
      if (!readerExists && !bookExists) {
        throw new IllegalArgumentException(
            "Не найдены ни читатель '" + readerName + "', ни книга '" + rEntityName + "'");
      } else if (!readerExists) {
        throw new IllegalArgumentException("Читатель не найден: " + readerName);
      } else if (!bookExists) {
        throw new IllegalArgumentException("Книга не найдена: " + rEntityName);
      } else {
        throw new IllegalArgumentException(
            "Активная выдача не найдена для читателя '" + readerName + "' и книги '" + rEntityName
                + "'");
      }
    }
  }

  @Override
  public List<ReadableEntity> getBorrowedByReader(String name) {
    String sql = """
        SELECT * FROM books b
        JOIN borrowings br ON b.id = br.book_id
        JOIN readers r ON br.reader_id = r.id
        WHERE LOWER(r.name) = LOWER(?)
        AND br.returned_at IS NULL
        """;
    return jdbcTemplate.query(sql, bookRowMapper(), name);
  }

  @Override
  public List<String[]> getTopReadableEntities() {
    String sql = """
        SELECT
            b.name,
            COUNT(br.id) AS borrow_count
        FROM books b
        JOIN borrowings br ON b.id = br.book_id
        GROUP BY b.id, b.name, b.author, b.created_at
        ORDER BY borrow_count DESC
        LIMIT 10
        """;
    return jdbcTemplate.query(sql, (rs, rowNum) -> new String[]{rs.getString("name"),
        String.valueOf(rs.getInt("borrow_count"))});
  }

  @Override
  public List<String[]> getBorrowedRE() {
    String sql = """
        SELECT b.name AS book_name,
            r.name AS reader_name
        FROM books b
        JOIN borrowings br ON b.id = br.book_id
        JOIN readers r ON br.reader_id = r.id
        WHERE br.returned_at IS NULL
        """;
    return jdbcTemplate.query(sql,
        (rs, rowNum) -> new String[]{rs.getString("book_name"), rs.getString("reader_name")});
  }

  @Override
  public Optional<Reader> findReaderById(Integer id) {
    String sql = "SELECT * FROM readers WHERE id = ? LIMIT 1";
    try {
      Reader reader = jdbcTemplate.queryForObject(sql, readerRowMapper(), id);
      return Optional.ofNullable(reader);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<ReadableEntity> findReadableEntityByName(String name) {
    String sql = "SELECT * FROM books WHERE LOWER(name) = LOWER(?) LIMIT 1";
    try {
      ReadableEntity entity = jdbcTemplate.queryForObject(sql, bookRowMapper(), name);
      return Optional.ofNullable(entity);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Reader> getReaders() {
    String sql = "SELECT * from readers";
    return jdbcTemplate.query(sql, readerRowMapper());
  }

  @Override
  public List<ReadableEntity> getReadableEntities() {
    String sql = "SELECT * from books";
    return jdbcTemplate.query(sql, bookRowMapper());
  }
}
