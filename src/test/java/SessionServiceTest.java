import dev.schlaubi.mojang_api.services.session.SessionProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SessionServiceTest extends AbstractMojangApiTest {

  @Test
  public void test() {
    SessionProfile session = api.getSessions().findSession("2683afcf9cf54c6f98a097765f439529");

    Assertions.assertNotNull(session);
    Assertions.assertEquals("DR_Schlaubi", session.getName());
  }
}
