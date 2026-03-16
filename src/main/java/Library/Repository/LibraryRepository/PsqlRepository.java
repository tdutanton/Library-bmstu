package Library.Repository.LibraryRepository;

import Library.Model.Domain.ReadableEntity.ReadableEntity;
import Library.Model.Domain.Reader.Reader;
import Library.Model.Factory.ReadableEntityFactory.ReadableEntityFactory;
import Library.Model.Factory.ReadableEntityFactory.ReadableEntityParam;
import Library.Model.Factory.ReaderFactory.ReaderFactory;
import Library.Model.Factory.ReaderFactory.ReaderParam;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
      LocalDateTime dt = rs.getTimestamp("created_at") != null ?
          rs.getTimestamp("created_at").toLocalDateTime() : null;

      ReadableEntityParam param = new ReadableEntityParam(id, name, author, dt);
      return readableEntityFactory.createReadableEntity(param);
    };
  }

  private RowMapper<Reader> readerRowMapper() {
    return (ResultSet rs, int rowNum) -> {
      Integer id = rs.getInt("id");
      String name = rs.getString("name");
      String email = rs.getString("email");
      LocalDateTime dt = rs.getTimestamp("created_at") != null ?
          rs.getTimestamp("created_at").toLocalDateTime() : null;

      ReaderParam param = new ReaderParam(id, name, email, dt);
      return readerFactory.createReader(param);
    };
  }

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

  public boolean readerExistsByName(String name) {
    if (name == null) {
      return false;
    }
    String sql = "SELECT 1 FROM readers WHERE name = ? LIMIT 1";
    try {
      jdbcTemplate.queryForObject(sql, String.class, name);
      return true;
    } catch (EmptyResultDataAccessException e) {
      return false;
    }
  }

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
  public Integer createReader(Reader reader) {
    String sql = "INSERT INTO readers (name, email, created_at) VALUES (?, ?, ?) RETURNING id";

    return jdbcTemplate.queryForObject(sql, Integer.class,
        reader.getName(),
        reader.getEmail(),
        reader.getCreatedAt()
    );
  }

  @Override
  public Integer createReadableEntity(ReadableEntity entity) {
    String sql = "INSERT INTO books (name, author, created_at) VALUES (?, ?, ?) RETURNING id";

    return jdbcTemplate.queryForObject(sql, Integer.class,
        entity.getName(),
        entity.getAuthor(),
        entity.getCreatedAt()
    );
  }

  public Optional<Reader> findReaderById(Integer id) {
    String sql = "SELECT * FROM readers WHERE id = ?";
    try {
      Reader reader = jdbcTemplate.queryForObject(sql, readerRowMapper(), id);
      return Optional.ofNullable(reader);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  public Optional<ReadableEntity> findReadableEntityByName(String name) {
    String sql = "SELECT * FROM books WHERE name = ?";
    try {
      ReadableEntity entity = jdbcTemplate.queryForObject(sql, bookRowMapper(), name);
      return Optional.ofNullable(entity);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }
}
