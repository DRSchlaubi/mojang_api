package dev.schlaubi.mojang_api.utils;

import dev.schlaubi.mojang_api.services.session.SessionProfile.Property;
import dev.schlaubi.mojang_api.services.session.Textures;
import java.io.IOException;
import java.util.Base64;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class to decode {@link dev.schlaubi.mojang_api.services.session.Textures}.
 */
public class TextureUtils {

  /**
   * Decodes the {@link Textures} of a {@link Property}.
   *
   * @param property the property containing the textures
   * @return the new textures
   * @throws IOException see {@link com.fasterxml.jackson.databind.ObjectMapper#readValue(byte[],
   * Class)}
   */
  @NotNull
  public static Textures decodeTextures(@NotNull Property property) throws IOException {
    return decodeTextures(property.getValue());
  }

  /**
   * Decodes the {@link Textures} of a {@link Property}.
   *
   * @param input the string containing the textures
   * @return the new textures
   * @throws IOException see {@link com.fasterxml.jackson.databind.ObjectMapper#readValue(byte[],
   * Class)}
   */
  @NotNull
  public static Textures decodeTextures(@NotNull String input) throws IOException {
    return decodeTextures(input.getBytes());
  }

  /**
   * Decodes the {@link Textures} of a {@link Property}.
   *
   * @param bytes the bytes containing the textures
   * @return the new textures
   * @throws IOException see {@link com.fasterxml.jackson.databind.ObjectMapper#readValue(byte[],
   * Class)}
   */
  @NotNull
  public static Textures decodeTextures(byte[] bytes) throws IOException {
    byte[] decoded = Base64.getDecoder().decode(bytes);

    return Json.decodeJson(Textures.class, decoded);
  }
}
