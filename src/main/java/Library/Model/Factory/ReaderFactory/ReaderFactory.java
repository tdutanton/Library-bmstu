package Library.Model.Factory.ReaderFactory;

import Library.Model.Domain.Reader.Reader;

public interface ReaderFactory {

  Reader createReader(ReaderParam param);
}
