package Library.Model.Factory.ReaderFactory;

import Library.Model.Domain.Reader.Reader;

/**
 * Фабрика для создания читателей
 */
public interface ReaderFactory {

  Reader createReader(ReaderParam param);
}
