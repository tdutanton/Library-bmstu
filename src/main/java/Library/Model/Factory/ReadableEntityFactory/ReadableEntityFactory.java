package Library.Model.Factory.ReadableEntityFactory;

import Library.Model.Domain.ReadableEntity.ReadableEntity;

/**
 * Фабрика для создания читаемых сущностей
 */
public interface ReadableEntityFactory {

  ReadableEntity createReadableEntity(ReadableEntityParam param);
}
