package tovar.application.service.cloud;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.mutiny.Uni;
import tovar.domain.service.ICloudStrategy;

public abstract class AbstractCloudService implements ICloudStrategy {

  public static final Logger log = LoggerFactory.getLogger(AbstractCloudService.class);

  @Override
  public Uni<Void> upload(String pathFile) {
    return Uni.createFrom().voidItem()
        .invoke(() -> checkFile(pathFile))
        .chain(() -> uploadProcess(pathFile))
        .invoke(() -> tracer(pathFile));
  }

  protected void checkFile(String target) {
    if (target == null || !(new File(target)).isFile()) {
      throw new IllegalArgumentException("Invalid file path");
    }
  }

  // Método abstracto para implementar en clases concretas
  public abstract Uni<Void> uploadProcess(String pathFile);

  protected void tracer(String pathFile) {
    log.info(String.format("File (%s) sent to cloud", pathFile));
  }
}
