package tovar.application.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import tovar.domain.model.event.GeneratedReportEvent;
import tovar.domain.service.ICloudStrategy;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyString;

@QuarkusTest
public class CloudEventHandlerTest {

  class TestCloudEventHandler extends CloudEventHandler {

    @Override
    protected ICloudStrategy getCloudService() {
      return cloudStrategy;
    }

  }

  @InjectMock
  private ICloudStrategy cloudStrategy;
  @Mock
  private GeneratedReportEvent generatedReportEvent;

  private CloudEventHandler handler;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    handler = new TestCloudEventHandler();
  }

  @Test
  public void ItSuccessfulUpload() {
    when(generatedReportEvent.getPathFile()).thenReturn("path/to/file");
    when(cloudStrategy.upload(anyString())).thenReturn(Uni.createFrom().nullItem());
    handler.handleEvent(generatedReportEvent);
    verify(cloudStrategy, times(1)).upload("path/to/file");
  }

  @Test
  public void ItFailedUpload() {
    when(generatedReportEvent.getPathFile()).thenReturn("path/to/file");
    when(cloudStrategy.upload(anyString())).thenReturn(Uni.createFrom().failure(new RuntimeException("Upload error")));
    handler.handleEvent(generatedReportEvent);
    verify(cloudStrategy, times(1)).upload("path/to/file");
  }
}
