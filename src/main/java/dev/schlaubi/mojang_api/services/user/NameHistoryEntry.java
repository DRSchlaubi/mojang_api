package dev.schlaubi.mojang_api.services.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.schlaubi.mojang_api.utils.InstantDeserializer;
import java.time.Instant;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Representation of an entry of the user name history.
 *
 * @see UserService
 * @see UserService#findUserByNameAsync(String)
 * @see UserService#findUserByName(String)
 */
public class NameHistoryEntry {

  private final String name;
  @JsonDeserialize(using = InstantDeserializer.class)
  private final Instant changedToAt;

  /**
   * Constructs new NameHistoryEntry
   *
   * @param name the name
   * @param changedToAt the timestamp when the user changed it's name to this or {@code null} if it
   * was the initial name
   */
  @JsonCreator
  public NameHistoryEntry(@JsonProperty("name") String name,
      @JsonProperty("changedToAt") Instant changedToAt) {
    this.name = name;
    this.changedToAt = changedToAt;
  }

  /**
   * The name
   *
   * @return the name.
   */
  @NotNull
  public String getName() {
    return name;
  }

  /**
   * The timestamp when the user changed it's name to this or {@code null} if it was the initial
   * name
   *
   * @return the timestamp
   */
  @Nullable
  public Instant getChangedToAt() {
    return changedToAt;
  }

  @Override
  public boolean equals(@Nullable Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NameHistoryEntry)) {
      return false;
    }
    NameHistoryEntry that = (NameHistoryEntry) o;
    return Objects.equals(name, that.name) && Objects
        .equals(changedToAt, that.changedToAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, changedToAt);
  }

  @Override
  public String toString() {
    return "NameHistoryEntry{" +
        "name='" + name + '\'' +
        ", changedToAt=" + changedToAt +
        '}';
  }
}
