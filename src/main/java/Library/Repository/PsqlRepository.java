package Library.Repository;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PsqlRepository implements LibraryRepository {

  private final ReaderFactory readerFactory;
  private final ReadableEntityFactory readableEntityFactory;

  private final RowMapper<Reader> readerRowMapper = (ResultSet rs, int rowNum) -> {
    Integer id = rs.getInt("id");
    String name = rs.getString("name");
    String email = rs.getString("email");
    LocalDateTime dt = rs.getTimestamp("created_at") != null ?
        rs.getTimestamp("created_at").toLocalDateTime() : null;

    ReaderParam param = new ReaderParam(id, name, email, dt);

    return readerFactory.createReader(param);
  };

  private final RowMapper<ReadableEntity> bookRowMapper = (ResultSet rs, int rowNum) -> {
    Integer id = rs.getInt("id");
    String name = rs.getString("name");
    String author = rs.getString("author");
    LocalDateTime dt = rs.getTimestamp("created_at") != null ?
        rs.getTimestamp("created_at").toLocalDateTime() : null;

    ReadableEntityParam param = new ReadableEntityParam(id, name, author, dt);

    return readableEntityFactory.createReadableEntity(param);
  };

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void createReader(Reader reader) {
    String sql = "INSERT INTO readers (id, name, email, created_at) VALUES (?, ?, ?, ?)";

    jdbcTemplate.update(sql,
        reader.getId(),
        reader.getName(),
        reader.getEmail(),
        reader.getCreatedAt()
    );
  }

  @Override
  public void createReadableEntity(ReadableEntity entity) {
    String sql = "INSERT INTO books (id, name, author, created_at) VALUES (?, ?, ?, ?)";

    jdbcTemplate.update(sql,
        entity.getId(),
        entity.getName(),
        entity.getAuthor(),
        entity.getCreatedAt()
    );
  }

  public Optional<Reader> findReaderById(Integer id) {
    String sql = "SELECT * FROM readers WHERE id = ?";
    try {
      Reader reader = jdbcTemplate.queryForObject(sql, readerRowMapper, id);
      return Optional.ofNullable(reader);
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  public Optional<ReadableEntity> findReadableEntityByName(String name) {
    String sql = "SELECT * FROM books WHERE id = ?";
    try {
      ReadableEntity entity = jdbcTemplate.queryForObject(sql, bookRowMapper, name);
      return Optional.ofNullable(entity);
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}
