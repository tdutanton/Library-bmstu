package Library.Service.ReadableEntityService;

import Library.Model.Factory.ReadableEntityFactory.ReadableEntityFactory;
import Library.Repository.LibraryRepository.LibraryRepository;
import Library.Utils.Logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadableEntityService {

  private final LibraryRepository libraryRepository;
  private final ReadableEntityFactory readableEntityFactory;
  private final Logger logger;

}
