import dev.schlaubi.mojang_api.services.status.StatusSummary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StatusTest extends AbstractMojangApiTest {

  @Test
  public void runStatusTest() {
    StatusSummary status = api.getStatus().check();

    Assertions.assertNotNull(status.getStatus(StatusSummary.MINECRAFT));
    Assertions.assertNotNull(status.getStatus(StatusSummary.MINECRAFT_SESSIONS));
    Assertions.assertNotNull(status.getStatus(StatusSummary.MOJANG_ACCOUNTS));
    Assertions.assertNotNull(status.getStatus(StatusSummary.MOJANG_AUTH));
    Assertions.assertNotNull(status.getStatus(StatusSummary.MOJANG_SESSIONS));
    Assertions.assertNotNull(status.getStatus(StatusSummary.MOJANG_API));
    Assertions.assertNotNull(status.getStatus(StatusSummary.MINECRAFT_TEXTURES));
    Assertions.assertNotNull(status.getStatus(StatusSummary.MOJANG));
  }
}
