import dev.schlaubi.mojang_api.MojangApi;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class AbstractMojangApiTest {

  protected static MojangApi api;

  @BeforeAll
  public static void setup() {
    api = MojangApi.create();
  }

  @AfterAll
  public static void die() {
    api.close();
  }
}
