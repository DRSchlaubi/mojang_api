import dev.schlaubi.mojang_api.services.user.NameHistoryEntry;
import dev.schlaubi.mojang_api.services.user.User;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest extends AbstractMojangApiTest {

  @Test
  public void testFindByName() {
    User me = api.getUsers().findUserByName("dr_schlaubi");

    Assertions.assertNotNull(me);
    Assertions.assertFalse(me.isDemo());
    Assertions.assertFalse(me.isLegacy());
    Assertions.assertEquals(me.getName(), "DR_Schlaubi");
    System.out.println(me.getUuid());
  }

  @Test
  public void testFindById() {
    List<NameHistoryEntry> me = api.getUsers()
        .findUserNamesById("2683afcf-9cf5-4c6f-98a0-97765f439529");
    Assertions.assertNotNull(me);

    NameHistoryEntry first = me.get(0);

    Assertions.assertEquals("sheeplover7", first.getName());
  }
}
