package Library.Model.Factory.ReaderFactory;

import Library.Model.Domain.Reader.Reader;
import Library.Model.Domain.Reader.ReaderTxtEntity;
import Library.Model.Domain.TextEntity.Email;
import Library.Model.Domain.TextEntity.Name;
import org.springframework.stereotype.Component;

/**
 * Реализация фабрики читателей с параметром ReaderParam
 */
@Component
public class ReaderFactoryStringParam implements ReaderFactory {

  public Reader createReader(ReaderParam param) {
    return new ReaderTxtEntity(param.id(), new Name(param.name()), new Email(param.email()),
        param.createdAt());
  }

}
