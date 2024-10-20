package tovar.application.handler;

import tovar.domain.model.event.LogLevel;

public class ConsoleLogger extends LoggerHandler {
  @Override
  public void logMessage(LogLevel level, String message) {
    System.out.println("Console Logger[" + level + "]: " + message);
    passToNext(level, message);
  }
}
