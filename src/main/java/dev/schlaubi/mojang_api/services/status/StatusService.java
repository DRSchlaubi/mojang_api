package dev.schlaubi.mojang_api.services.status;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;
import retrofit2.http.GET;

/**
 * A service for interaction with the Mojang Status api.
 */
public interface StatusService {

  /**
   * The base url of all API endpoints used by this service.
   */
  String BASE_URL = "https://status.mojang.com";

  /**
   * Asynchronously checks for the current status of mojang services
   *
   * @return a {@link CompletableFuture} containing the {@link StatusSummary} representing the
   * status
   */
  @NotNull
  @GET("check")
  CompletableFuture<StatusSummary> checkAsync();

  /**
   * Synchronously checks for the current status of mojang services
   *
   * @return the {@link StatusSummary} representing the status
   * @see CompletableFuture#join()
   */
  default StatusSummary check() {
    return checkAsync().join();
  }
}
