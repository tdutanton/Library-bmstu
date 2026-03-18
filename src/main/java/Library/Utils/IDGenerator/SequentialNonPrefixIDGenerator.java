package Library.Utils.IDGenerator;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SequentialNonPrefixIDGenerator implements IDGenerator {

  private Integer currentId = 1;

  @Override
  public synchronized String nextId() {
    String result = String.valueOf(currentId);
    currentId++;
    return result;
  }

  @Override
  public synchronized Integer nextNumberId() {
    return currentId++;
  }
}

