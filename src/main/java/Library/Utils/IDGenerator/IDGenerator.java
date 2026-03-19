package Library.Utils.IDGenerator;

/**
 * Генератор id для объектов
 */
public interface IDGenerator {

  /**
   * id в виде строки
   *
   * @return
   */
  String nextId();

  /**
   * id в виде Integer
   *
   * @return
   */
  Integer nextNumberId();
}
