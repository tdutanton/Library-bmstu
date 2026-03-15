package Library.Utils.IDGenerator;

public class SequentialNonPrefixIDGenerator implements IDGenerator {

  private long currentId = 1;

  @Override
  public synchronized String nextId() {
    String result = String.valueOf(currentId);
    currentId++;
    return result;
  }

  @Override
  public synchronized long nextNumberId() {
    return currentId++;
  }
}

