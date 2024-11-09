package tovar.domain.service;

import io.smallrye.mutiny.Uni;

public interface ICloudStrategy {
  Uni<Void> upload(String filePath);
}
