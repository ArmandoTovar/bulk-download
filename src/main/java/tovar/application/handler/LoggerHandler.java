package tovar.application.handler;

import tovar.domain.model.event.LogLevel;

public abstract class LoggerHandler {
  protected LoggerHandler nextHandler;

  public void setNextHandler(LoggerHandler nextHandler) {
    this.nextHandler = nextHandler;
  }

  public abstract void logMessage(LogLevel level, String message);

  protected void passToNext(LogLevel level, String message) {
    if (nextHandler != null) {
      nextHandler.logMessage(level, message);
    }
  }
}
