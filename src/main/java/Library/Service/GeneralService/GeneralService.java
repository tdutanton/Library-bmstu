package Library.Service.GeneralService;

import Library.Model.Factory.ReaderFactory.ReaderParam;
import Library.Service.ReadableEntityService.ReadableEntityService;
import Library.Service.ReaderService.ReaderService;
import Library.Utils.Logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralService {

  private final ReaderService readerService;
  private final ReadableEntityService readableEntityService;
  private final Logger logger;

  public void createReader(String name, String email) {
    ReaderParam param = ReaderParam.forNew(name, email);
    Integer id = readerService.createReader(param);
    logger.info("Читатель создан, id = " + id);
  }


}
