package Library.Model.Factory.ReadableEntityFactory;

import Library.Model.Domain.ReadableEntity.ReadableEntity;

public interface ReadableEntityFactory {

  ReadableEntity createReadableEntity(ReadableEntityParam param);
}
