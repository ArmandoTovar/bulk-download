// package tovar.application.handler;
//
// import java.io.FileWriter;
// import java.io.IOException;
// import tovar.domain.model.event.LogLevel;
//
// public class FileLogger extends LoggerHandler {
// private String filePath = "logs.txt";
//
// @Override
// public void logMessage(LogLevel level, String message) {
// try (FileWriter fw = new FileWriter(filePath, true)) {
// fw.write("File Logger[" + level + "]: " + message + "\n");
// } catch (IOException e) {
// e.printStackTrace();
// }
// passToNext(level, message);
// }
// }
