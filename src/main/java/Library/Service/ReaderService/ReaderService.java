package Library.Service.ReaderService;

import Library.Model.Domain.Reader.Reader;
import Library.Model.Factory.ReaderFactory.ReaderFactory;
import Library.Model.Factory.ReaderFactory.ReaderParam;
import Library.Repository.LibraryRepository.LibraryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Сервис для реализации бизнес-логики с использованием репозитория Только для операций с
 * читателями
 */
@Service
@RequiredArgsConstructor
public class ReaderService {

  private final LibraryRepository libraryRepository;
  private final ReaderFactory readerFactory;

  public Integer createReader(ReaderParam param) {
    if (libraryRepository.readerExistsByName(param.name())) {
      throw new RuntimeException("Читатель с таким именем уже существует");
    }
    return libraryRepository.createReader(readerFactory.createReader(param));
  }

  public List<Reader> getReaders() {
    return libraryRepository.getReaders();
  }

  public boolean readerExists(String name) {
    return libraryRepository.readerExistsByName(name);
  }
}
