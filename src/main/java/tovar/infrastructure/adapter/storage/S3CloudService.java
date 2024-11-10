package tovar.infrastructure.adapter.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import tovar.application.service.cloud.AbstractCloudService;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.async.AsyncRequestBody;

@ApplicationScoped
public class S3CloudService extends AbstractCloudService {
  @Inject
  S3AsyncClient s3;

  @ConfigProperty(name = "bucket.report.name")
  String bucketName;

  private PutObjectRequest buildPutRequest(File file) {
    String contentType;
    try {
      contentType = Files.probeContentType(file.toPath());
    } catch (IOException e) {
      contentType = "application/octet-stream";
    }
    return PutObjectRequest.builder()
        .bucket(bucketName)
        .key(file.getName())
        .contentType(contentType)
        .build();
  }

  @Override
  @WithTransaction
  public Uni<Void> uploadProcess(String pathFile) {
    File file = new File(pathFile);
    return Uni.createFrom()
        .completionStage(() -> s3.putObject(buildPutRequest(file), AsyncRequestBody.fromFile(file)))
        .onItem().ignore().andSwitchTo(Uni.createFrom().voidItem())
        .invoke(() -> {
        });
  }

}
