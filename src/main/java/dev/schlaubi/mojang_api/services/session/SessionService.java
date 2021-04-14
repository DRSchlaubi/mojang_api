package dev.schlaubi.mojang_api.services.session;

import dev.schlaubi.mojang_api.MojangApi;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Wrapper for {@code sessionserver.mojang.com} endpoints.
 *
 * @see MojangApi#getSessions()
 */
public interface SessionService {

  /**
   * The base url of all API endpoints used by this service.
   */
  String BASE_URL = "https://sessionserver.mojang.com/session/minecraft/profile/";

  /**
   * Asynchronously fetches the session UUID.
   *
   * @param uuid the uuid of the user to look up
   * @param unsigned whether you want to sign the response or not (default true)
   * @return a {@link CompletableFuture} containing a {@link SessionProfile} representing the
   * session or {@code null} if there was no user with that id
   */
  @NotNull
  @GET("{uuid}")
  CompletableFuture<@Nullable SessionProfile> findSessionAsync(@NotNull @Path("uuid") String uuid,
      @Query("unsigned") boolean unsigned);

  /**
   * Asynchronously fetches the session UUID without signing.
   *
   * @param uuid the uuid of the user to look up
   * @return a {@link CompletableFuture} containing a {@link SessionProfile} representing the
   * session or {@code null} if there was no user with that id
   */
  @NotNull
  default CompletableFuture<@Nullable SessionProfile> findSessionAsync(
      @NotNull @Path("uuid") String uuid) {
    return findSessionAsync(uuid, true);
  }

  /**
   * Synchronously fetches the session UUID.
   *
   * @param uuid the uuid of the user to look up
   * @param unsigned whether you want to sign the response or not (default true) session or {@code
   * null} if there was no user with that id
   * @return a {@link SessionProfile} representing the
   */
  @Nullable
  default SessionProfile findSession(@NotNull String uuid, boolean unsigned) {
    Objects.requireNonNull(uuid, "uuid may not be null");
    return findSessionAsync(uuid, unsigned).join();
  }

  /**
   * Synchronously fetches the session UUID without signing.
   *
   * @param uuid the uuid of the user to look up
   * @return a {@link CompletableFuture} containing a {@link SessionProfile} representing the
   * session or {@code null} if there was no user with that id
   */
  @Nullable
  default SessionProfile findSession(@NotNull String uuid) {
    Objects.requireNonNull(uuid, "uuid may not be null");
    return findSessionAsync(uuid, false).join();
  }

  /**
   * Asynchronously fetches the session UUID.
   *
   * @param uuid the uuid of the user to look up
   * @param unsigned whether you want to sign the response or not (default true)
   * @return a {@link CompletableFuture} containing a {@link SessionProfile} representing the
   * session or {@code null} if there was no user with that id
   */
  @NotNull
  default CompletableFuture<@Nullable SessionProfile> findSessionAsync(@NotNull UUID uuid,
      boolean unsigned) {
    return findSessionAsync(uuid.toString(), unsigned);
  }

  /**
   * Asynchronously fetches the session UUID without signing.
   *
   * @param uuid the uuid of the user to look up
   * @return a {@link CompletableFuture} containing a {@link SessionProfile} representing the
   * session or {@code null} if there was no user with that id
   */
  @NotNull
  default CompletableFuture<@Nullable SessionProfile> findSessionAsync(@NotNull UUID uuid) {
    return findSessionAsync(uuid, false);
  }

  /**
   * ASynchronously fetches the session UUID without signing.
   *
   * @param uuid the uuid of the user to look up
   * @param unsigned whether you want to sign the response or not (default true)
   * @return a {@link CompletableFuture} containing a {@link SessionProfile} representing the
   * session or {@code null} if there was no user with that id
   */
  @Nullable
  default SessionProfile findSession(@NotNull UUID uuid, boolean unsigned) {
    return findSessionAsync(uuid, unsigned).join();
  }

  /**
   * Synchronously fetches the session UUID without signing.
   *
   * @param uuid the uuid of the user to look up
   * @return a {@link SessionProfile} representing the session or {@code null} if there was no user
   * with that id
   */
  @Nullable
  default SessionProfile findSession(@NotNull UUID uuid) {
    return findSessionAsync(uuid).join();
  }
}
