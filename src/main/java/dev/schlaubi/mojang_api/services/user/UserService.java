package dev.schlaubi.mojang_api.services.user;

import dev.schlaubi.mojang_api.MojangApi;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Wrapper for {@code api.mojang.com}.
 *
 * @see MojangApi#getUsers()
 */
public interface UserService {

  /**
   * The base url of all API endpoints used by this service.
   */
  String BASE_URL = "https://api.mojang.com/";

  /**
   * Asynchronously fetches the {@link User user profile} by the name.
   *
   * @return a {@link CompletableFuture} containing the {@link User} representing the user profile
   * or {@code null} if there was no user with that id
   */
  @NotNull
  @GET("users/profiles/minecraft/{username}")
  CompletableFuture<@Nullable User> findUserByNameAsync(@NotNull @Path("username") String name);

  /**
   * Synchronously fetches the {@link User user profile} by the name.
   *
   * @param name the name to look up
   * @return the {@link User} representing the user profile or {@code null} if there was no user
   * with that id
   * @throws NullPointerException if name is null
   * @see CompletableFuture#join()
   */
  @Nullable
  default User findUserByName(@NotNull String name) {
    Objects.requireNonNull(name, "name may not be null");
    return findUserByNameAsync(name).join();
  }

  /**
   * Asynchronously fetches the name history of a UUID.
   *
   * @param uuid the uuid of the user to look up
   * @return a {@link CompletableFuture} containing a {@link List} of {@link NameHistoryEntry}
   * representing the name history or {@code null} if there was no user with that id
   */
  @NotNull
  @GET("user/profiles/{uuid}/names")
  CompletableFuture<@Nullable List<NameHistoryEntry>> findUserNamesByIdAsync(
      @NotNull @Path("uuid") String uuid);

  /**
   * Synchronously fetches the name history of a UUID.
   *
   * @param uuid the uuid of the user to look up
   * @return a {@link List} of {@link NameHistoryEntry} representing the name history or {@code
   * null} if there was no user with that id
   * @throws NullPointerException if uuid is null
   * @see CompletableFuture#join()
   */
  @Nullable
  default List<NameHistoryEntry> findUserNamesById(@NotNull String uuid) {
    Objects.requireNonNull(uuid, "uuid may not be null");
    return findUserNamesByIdAsync(uuid).join();
  }

  /**
   * Asynchronously fetches the name history of a UUID.
   *
   * @param uuid the uuid of the user to look up
   * @return a {@link CompletableFuture} containing a {@link List} of {@link NameHistoryEntry}
   * representing the name history or {@code null} if there was no user with that id
   * @throws NullPointerException if uuid is null
   */
  @NotNull
  default CompletableFuture<@Nullable List<NameHistoryEntry>> findUserNamesByIdAsync(
      @NotNull UUID uuid) {
    Objects.requireNonNull(uuid, "uuid may not be null");

    return findUserNamesByIdAsync(uuid.toString());
  }

  /**
   * Synchronously fetches the name history of a UUID.
   *
   * @param uuid the uuid of the user to look up
   * @return a {@link List} of {@link NameHistoryEntry} representing the name history or {@code
   * null} if there was no user with that id
   * @throws NullPointerException if uuid is null
   * @see CompletableFuture#join()
   */
  @Nullable
  default List<NameHistoryEntry> findUserNamesById(@NotNull UUID uuid) {
    Objects.requireNonNull(uuid, "uuid may not be null");

    return findUserNamesByIdAsync(uuid).join();
  }
}
