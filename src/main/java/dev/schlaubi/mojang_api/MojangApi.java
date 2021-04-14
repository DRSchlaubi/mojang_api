package dev.schlaubi.mojang_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.schlaubi.mojang_api.services.session.SessionService;
import dev.schlaubi.mojang_api.services.status.StatusService;
import dev.schlaubi.mojang_api.services.user.UserService;
import dev.schlaubi.mojang_api.utils.Json;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Wrapper for the <a href="http://wiki.vg/Mojang_API">Mojang API</a>
 */
public class MojangApi {

  private final OkHttpClient client;
  private final UserService users;
  private final StatusService status;
  private final SessionService sessions;

  MojangApi(OkHttpClient okHttpClient, ObjectMapper objectMapper) {
    this.client = okHttpClient;
    Json.setObjectMapper(objectMapper);
    Retrofit.Builder retrofit = new Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(JacksonConverterFactory.create(objectMapper));

    users = retrofit
        .baseUrl(UserService.BASE_URL)
        .build()
        .create(UserService.class);
    status = retrofit
        .baseUrl(StatusService.BASE_URL)
        .build()
        .create(StatusService.class);
    sessions = retrofit.baseUrl(SessionService.BASE_URL)
        .build()
        .create(SessionService.class);
  }

  /**
   * Creates a new instances of the API with default parameters.
   *
   * @return the new instance.
   */
  @NotNull
  public static MojangApi create() {
    return newBuilder().build();
  }

  /**
   * Creates a new {@link MojangApiBuilder}.
   *
   * @return the new builder
   * @see MojangApiBuilder#build()
   */
  @NotNull
  public static MojangApiBuilder newBuilder() {
    return new MojangApiBuilder();
  }

  /**
   * Returns a wrapper for {@code api.mojang.com}.
   *
   * @return a {@link UserService}
   * @see UserService
   */
  @NotNull
  public UserService getUsers() {
    return users;
  }

  /**
   * Returns a wrapper for {@code status.mojang.com}.
   *
   * @return a {@link StatusService}
   * @see StatusService
   */
  @NotNull
  public StatusService getStatus() {
    return status;
  }

  /**
   * Returns a wrapper for {@code sessionserver.mojang.com}.
   *
   * @return a {@link SessionService}
   * @see SessionService
   */
  @NotNull
  public SessionService getSessions() {
    return sessions;
  }

  /**
   * Closes all resources used by the client.
   */
  public void close() {
    client.connectionPool().evictAll();
    client.dispatcher().executorService().shutdown();
  }
}
