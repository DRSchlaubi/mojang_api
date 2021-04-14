package dev.schlaubi.mojang_api.services.session;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.schlaubi.mojang_api.utils.TextureUtils;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Representations of a Minecraft Session Profile.
 *
 * @see SessionService
 */
public class SessionProfile {

  /**
   * Name for textures property.
   */
  public static final String TEXTURES = "textures";
  private final String id;
  private final String name;
  private final List<Property> properties;

  @JsonCreator
  public SessionProfile(
      @JsonProperty("id") String id, @JsonProperty("name") String name,
      @JsonProperty("properties") List<Property> properties) {
    this.id = id;
    this.name = name;
    this.properties = properties;
  }

  /**
   * Finds the texture property if it exists
   *
   * @return the texture {@link Property} or {@code null} if there was none
   * @see TextureUtils#decodeTextures(Property)
   */
  @Nullable
  public Property getTextures() {
    return properties.stream().filter(it -> it.getName().equals(TEXTURES))
        .findFirst().orElse(null);
  }

  /**
   * A Session profile property.
   */
  public static class Property {

    private final String name;
    private final String value;
    private final String signature;

    @JsonCreator
    public Property(
        @NotNull @JsonProperty("name") String name,
        @NotNull @JsonProperty("value") String value,
        @Nullable @JsonProperty("signature") String signature
    ) {
      this.name = name;
      this.value = value;
      this.signature = signature;
    }

    /**
     * The name of the property.
     *
     * @return the name
     */
    @NotNull
    public String getName() {
      return name;
    }

    /**
     * The value of the property.
     *
     * @return the value
     */
    @NotNull
    public String getValue() {
      return value;
    }

    /**
     * The signature of the property if requested.
     * <b>In order to get the signature please ensure that {@link SessionService#findSessionAsync(UUID,
     * boolean)} gets the unsiged parameter as false</b>
     *
     * @return the signature or {@code null} if there was none
     */
    @Nullable
    public String getSignature() {
      return signature;
    }

    /**
     * Converts this into a Textures object
     *
     * @return the {@link Textures object}
     * @throws IOException See {@link TextureUtils#decodeTextures(Property)}
     * @see TextureUtils#decodeTextures(Property)
     */
    @NotNull
    public Textures asTextures() throws IOException {
      return TextureUtils.decodeTextures(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Property)) {
        return false;
      }
      Property property = (Property) o;
      return Objects.equals(name, property.name) && Objects
          .equals(value, property.value) && Objects.equals(signature, property.signature);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, value, signature);
    }

    @Override
    public String toString() {
      return "Property{" +
          "name='" + name + '\'' +
          ", value='" + value + '\'' +
          ", signature='" + signature + '\'' +
          '}';
    }
  }

  /**
   * Returns the non formatted id of the profile.
   * @return the id
   */
  @NotNull
  public String getId() {
    return id;
  }

  /**
   * Return the name of the profile owner.
   * @return the name
   */
  @NotNull
  public String getName() {
    return name;
  }

  /**
   * Returns all {@link Property properties} in this session.
   * @return the properties
   */
  @NotNull
  public List<Property> getProperties() {
    return properties;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SessionProfile)) {
      return false;
    }
    SessionProfile that = (SessionProfile) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name)
        && Objects.equals(properties, that.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, properties);
  }

  @Override
  public String toString() {
    return "SessionProfile{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", properties=" + properties +
        '}';
  }
}
