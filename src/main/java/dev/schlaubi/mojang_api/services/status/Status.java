package dev.schlaubi.mojang_api.services.status;

import java.util.Arrays;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Representation of a service status.
 */
public enum Status {
  GREEN("green"),
  YELLOW("yellow"),
  RED("red");

  /**
   * Finds a status where {@link Status#getSerialName()} matches serialName.
   *
   * @param serialName the serial name to lookup
   * @return the {@link Status} that was found
   * @throws IllegalArgumentException if there is no status with that name
   */
  public static Status forSerialName(@NotNull String serialName) {
    Objects.requireNonNull(serialName, "Serial name may not be null");

    return Arrays.stream(values()).filter(it -> it.getSerialName().equals(serialName)).findFirst()
        .orElseThrow(
            () -> new IllegalArgumentException("There is no status for serial name:" + serialName));
  }

  private final String serialName;

  Status(String serialName) {
    this.serialName = serialName;
  }

  /**
   * The name mojang uses to serialize this.
   * @return the serial name
   */
  public String getSerialName() {
    return serialName;
  }
}
