package dev.schlaubi.mojang_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Objects;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Builder for {@link MojangApi}.
 *
 * @see MojangApi#newBuilder()
 */
public class MojangApiBuilder {

  private OkHttpClient client = null;
  private ObjectMapper objectMapper = null;

  MojangApiBuilder() {
  }

  /**
   * The currently set {@link OkHttpClient} or {@code null} if not set.
   *
   * @return the client
   */
  @Nullable
  public OkHttpClient getClient() {
    return client;
  }

  /**
   * Sets the {@link OkHttpClient} used to connect to endpoints.
   * @param client the client
   * @return this builder
   */
  @NotNull
  public MojangApiBuilder setClient(@NotNull OkHttpClient client) {
    Objects.requireNonNull(client, "client may not be null");
    this.client = client;
    return this;
  }

  /**
   * The currently set {@link ObjectMapper} or {@code null} if not set.
   *
   * @return the object mapper
   */
  public ObjectMapper getObjectMapper() {
    return objectMapper;
  }

  /**
   * Sets the {@link ObjectMapper} used to connect to endpoints.
   * @param objectMapper the object mapper
   * @return this builder
   */
  public MojangApiBuilder setObjectMapper(ObjectMapper objectMapper) {
    Objects.requireNonNull(objectMapper, "objectMapper may not be null");
    this.objectMapper = objectMapper;
    return this;
  }

  /**
   * Creates a new instance of {@link MojangApi}.
   *
   * @return the new api instance.
   */
  public MojangApi build() {
    OkHttpClient safeClient = Objects
        .requireNonNullElseGet(client, OkHttpClient::new);
    ObjectMapper safeObjectMapper = Objects
        .requireNonNullElseGet(objectMapper, ObjectMapper::new);

    return new MojangApi(safeClient, safeObjectMapper);
  }
}
