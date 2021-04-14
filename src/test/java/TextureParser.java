import dev.schlaubi.mojang_api.services.session.Textures;
import dev.schlaubi.mojang_api.utils.TextureUtils;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TextureParser {

  @Test
  public void parse() throws IOException {
    String input = "ewogICJ0aW1lc3RhbXAiIDogMTYxODMzMzAwNDIyMSwKICAicHJvZmlsZUlkIiA6ICIyNjgzYWZjZjljZjU0YzZmOThhMDk3NzY1ZjQzOTUyOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJEUl9TY2hsYXViaSIsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82MWMwODIxN2Q3MTZiMjQ2MDE5ZTAyZjI0NmJlYjkxMjNiMDNhZmFiOWFlYjJmODM4YjMzNjFhYWVlNmRiYzQiCiAgICB9CiAgfQp9";

    Textures textures = TextureUtils.decodeTextures(input);

    Assertions.assertEquals("DR_Schlaubi", textures.getProfileName());
  }
}
