package Library.Utils.Logging;

import org.springframework.stereotype.Service;

@Service
public final class ConsoleLogger implements Logger {

  private static boolean logging = true;

  public void setLogMode(boolean state) {
    logging = state;
  }

  @Override
  public void info(String message) {
    if (logging) {
      System.out.println(message);
    }
  }
}