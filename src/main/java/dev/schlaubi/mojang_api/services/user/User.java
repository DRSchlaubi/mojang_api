package dev.schlaubi.mojang_api.services.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.schlaubi.mojang_api.utils.Lazy;
import dev.schlaubi.mojang_api.utils.LazyUUID;
import java.util.Objects;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Representation of a Minecraft user profile.
 *
 * @see UserService
 * @see UserService#findUserNamesByIdAsync(UUID)
 * @see UserService#findUserNamesById(UUID)
 * @see UserService#findUserNamesByIdAsync(String)
 * @see UserService#findUserNamesById(String)
 */
public class User {

  private final String id;
  private final String name;
  private final boolean legacy;
  private final boolean demo;
  private final Lazy<UUID> uuid = LazyUUID.of(this::getId);

  /**
   * Creates a new user
   *
   * @param id the user id (non formatted uuid)
   * @param name the user name
   * @param legacy whether or not the user still uses a non mojang/microsoft account (minecraft.net
   * account)
   * @param demo whether or not this account is a demo account (hasn't bought Minecraft)
   */
  @JsonCreator
  public User(
      @NotNull @JsonProperty("id") String id, @NotNull @JsonProperty("name") String name,
      @Nullable @JsonProperty("legacy") Boolean legacy,
      @Nullable @JsonProperty("demo") Boolean demo) {
    this.id = id;
    this.name = name;
    this.legacy = Objects.requireNonNullElse(legacy, false);
    this.demo = Objects.requireNonNullElse(demo, false);
  }


  /**
   * The user id (non formatted uuid)
   *
   * @return the user id
   * @see User#getUuid() for the formatted UUID
   */
  @NotNull
  public String getId() {
    return id;
  }

  /**
   * The user name
   *
   * @return the name
   */
  @NotNull
  public String getName() {
    return name;
  }

  /**
   * Whether or not the user still uses a non mojang/microsoft account (minecraft.net account)
   *
   * @return whether or not the user still uses a non mojang/microsoft account (minecraft.net
   * account)
   */
  public boolean isLegacy() {
    return legacy;
  }

  /**
   * Whether or not this account is a demo account (hasn't bought Minecraft)
   *
   * @return whether or not this account is a demo account (hasn't bought Minecraft)
   */
  public boolean isDemo() {
    return demo;
  }

  /**
   * Computes and returns the formatted {@link UUID}.
   *
   * @return the formatted uuid
   * @see User#getId()
   */
  @NotNull
  public UUID getUuid() {
    return uuid.get();
  }

  @Override
  public boolean equals(@Nullable Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }
    User user = (User) o;
    return legacy == user.legacy && demo == user.demo && Objects.equals(id, user.id)
        && Objects.equals(name, user.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, legacy, demo);
  }

  @Override
  public String toString() {
    return "User{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", legacy=" + legacy +
        ", demo=" + demo +
        '}';
  }
}
