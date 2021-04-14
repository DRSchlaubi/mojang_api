import dev.schlaubi.mojang_api.utils.LazyUUID;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UUIDConversionTest {

  @Test
  public void testUUIDConvert() {
    UUID origin = UUID.fromString("2683afcf-9cf5-4c6f-98a0-97765f439529");
    UUID actual = LazyUUID.of(() -> "2683afcf9cf54c6f98a097765f439529").get();
    Assertions.assertEquals(origin, actual);
  }
}
