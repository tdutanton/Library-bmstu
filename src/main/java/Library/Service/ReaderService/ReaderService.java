package Library.Service.ReaderService;

import Library.Model.Factory.ReaderFactory.ReaderFactory;
import Library.Model.Factory.ReaderFactory.ReaderParam;
import Library.Repository.LibraryRepository.LibraryRepository;
import Library.Utils.Logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReaderService {

  private final LibraryRepository libraryRepository;
  private final ReaderFactory readerFactory;
  private final Logger logger;

  public Integer createReader(ReaderParam param) {
    if (libraryRepository.readerExistsByName(param.name())) {
      throw new RuntimeException("Читатель с таким именем уже существует");
    }
    return libraryRepository.createReader(readerFactory.createReader(param));
  }
}
