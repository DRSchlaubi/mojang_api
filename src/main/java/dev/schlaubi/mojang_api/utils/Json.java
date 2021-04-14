package dev.schlaubi.mojang_api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for deserializing Json objects (Used by {@link TextureUtils}).
 */
public class Json {

  private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  /**
   * Sets the {@link ObjectMapper} instance used for deserialization.
   *
   * @param objectMapper the mapper instance
   * @throws NullPointerException if the specified mapper is {@code null}
   */
  public static void setObjectMapper(@NotNull ObjectMapper objectMapper) {
    Objects.requireNonNull(objectMapper, "Object mapper may not be null");
    OBJECT_MAPPER = objectMapper;
  }

  /**
   * Decodes a {@code Json} string.
   *
   * @param clazz the {@link Class} of the type to deserialize
   * @param json the byte array containing the json
   * @param <T> the type of the object to deserialize
   * @return a new instance of T
   * @throws IOException See {@link ObjectMapper#readValue(byte[], Class)}
   */
  public static <T> T decodeJson(Class<T> clazz, byte[] json) throws IOException {
    return OBJECT_MAPPER.readValue(json, clazz);
  }
}
