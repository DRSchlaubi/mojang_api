package dev.schlaubi.mojang_api.utils;

import java.util.UUID;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Wrapper for {@link Lazy} for {@link UUID} which  can parse uuids without dashes.
 */
public class LazyUUID extends Lazy<UUID> {

  public static final Pattern UUID_PATTERN = Pattern
      .compile("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})");

  LazyUUID(Supplier<String> nonFormattedUUID) {
    super(() -> formatUuid(nonFormattedUUID.get()));
  }

  /**
   * Creates a new instance.
   *
   * @param nonFormattedUUID the {@link Supplier} producing the uuid
   * @return a {@link Lazy} instance.
   */
  @NotNull
  @Contract(value = "_ -> new", pure = true)
  public static Lazy<UUID> of(Supplier<String> nonFormattedUUID) {
    return new LazyUUID(nonFormattedUUID);
  }

  @NotNull
  private static UUID formatUuid(String id) {
    String withDashes = UUID_PATTERN.matcher(id).replaceAll("$1-$2-$3-$4-$5");

    return UUID.fromString(withDashes);
  }
}
