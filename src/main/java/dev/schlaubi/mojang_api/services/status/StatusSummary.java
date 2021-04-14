package dev.schlaubi.mojang_api.services.status;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.ArrayNode;
import dev.schlaubi.mojang_api.services.status.StatusSummary.Deserializer;
import dev.schlaubi.mojang_api.utils.NullCheck;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A summary representing the status of Mojang services.
 *
 * @see StatusService
 * @see StatusService#checkAsync()
 */
@JsonDeserialize(using = Deserializer.class)
public class StatusSummary {

  /**
   * The name for {@link StatusSummary#getStatus(String)} for the Minecraft website.
   */
  public static final String MINECRAFT = "minecraft.net";

  /**
   * The name for {@link StatusSummary#getStatus(String)} for the Minecraft session server.
   */
  public static final String MINECRAFT_SESSIONS = "session.minecraft.net";

  /**
   * The name for {@link StatusSummary#getStatus(String)} for the Mojang account website.
   */
  public static final String MOJANG_ACCOUNTS = "account.mojang.com";

  /**
   * The name for {@link StatusSummary#getStatus(String)} for the authentication servers.
   */
  public static final String MOJANG_AUTH = "authserver.mojang.com";

  /**
   * The name for {@link StatusSummary#getStatus(String)} for the session servers.
   */
  public static final String MOJANG_SESSIONS = "sessionserver.mojang.com";

  /**
   * The name for {@link StatusSummary#getStatus(String)} for the Mojang API.
   */
  public static final String MOJANG_API = "api.mojang.com";

  /**
   * The name for {@link StatusSummary#getStatus(String)} for the Minecraft (skin/cape) textures.
   */
  public static final String MINECRAFT_TEXTURES = "textures.minecraft.net";

  /**
   * The name for {@link StatusSummary#getStatus(String)} for the Mojang website.
   */
  public static final String MOJANG = "mojang.com";

  private final Map<String, Status> status;

  private StatusSummary(Map<String, Status> status) {
    this.status = status;
  }

  /**
   * Returns the status for this name or {@code null} if the status was found.
   *
   * @param name the name of the service to lookup the status for
   * @return the {@link Status} of the service
   * @throws NullPointerException if name is null
   */
  @Nullable
  public Status getStatus(@NotNull String name) {
    Objects.requireNonNull(name, "Name may not be null");
    return status.get(name);
  }

  /**
   * Checks whether the service has {@link Status#GREEN},
   *
   * @param name the name of the service to lookup the status for
   * @return whether the service is online or not
   * @throws NullPointerException if name is null
   */
  public boolean isOnline(@NotNull String name) {
    Status output = getStatus(name);
    Status safeStatus = NullCheck.requireNonNullElse(output, Status.RED);

    return safeStatus == Status.GREEN;
  }

  /**
   * Returns a map containing all statuses for all services.
   *
   * @return the map
   */
  public Map<String, Status> getStatus() {
    return status;
  }

  @Override
  public boolean equals(@Nullable Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof StatusSummary)) {
      return false;
    }
    StatusSummary that = (StatusSummary) o;
    return Objects.equals(status, that.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status);
  }

  @Override
  public String toString() {
    return "StatusSummary{" +
        "status=" + status +
        '}';
  }

  public static class Deserializer extends JsonDeserializer<StatusSummary> {

    @Override
    public StatusSummary deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      ArrayNode array = p.readValueAsTree();
      Map<String, Status> map = StreamSupport
          .stream(array.spliterator(), true)
          .map(this::parseElement)
          .filter(Objects::nonNull)
          .collect(Collectors
              .toMap(Entry::getKey, it -> Status.forSerialName(it.getValue().asText())));
      return new StatusSummary(Collections.unmodifiableMap(map));
    }

    @Nullable
    private Entry<String, JsonNode> parseElement(JsonNode node) {
      try {
        return node.fields().next();
      } catch (NoSuchElementException ignored) {
        return null;
      }
    }
  }
}
