package dev.schlaubi.mojang_api.services.session;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.schlaubi.mojang_api.services.session.SessionProfile.Property;
import dev.schlaubi.mojang_api.services.session.Textures.Texture.Type;
import dev.schlaubi.mojang_api.utils.InstantDeserializer;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Representation of the deserialized value of a textures property.
 *
 * @see SessionProfile#getTextures()
 * @see Property#asTextures()
 */
public class Textures {

  @JsonDeserialize(using = InstantDeserializer.class)
  private final Instant timestamp;
  private final String profileId;
  private final String profileName;
  private final boolean signatureRequired;
  private final Map<Texture.Type, Texture> textures;

  @JsonCreator
  public Textures(
      @NotNull @JsonProperty("timestamp") Instant timestamp,
      @NotNull @JsonProperty("profileId") String profileId,
      @NotNull @JsonProperty("profileName") String profileName,
      @Nullable @JsonProperty("signatureRequired") Boolean signatureRequired,
      @NotNull @JsonProperty("textures") Map<Texture.Type, Texture> textures
  ) {
    this.timestamp = timestamp;
    this.profileId = profileId;
    this.profileName = profileName;
    this.signatureRequired = Objects.requireNonNullElse(signatureRequired, false);
    this.textures = textures;
  }

  /**
   * Representation of a texture.
   */
  public static class Texture {

    private final String url;

    @JsonCreator
    public Texture(@NotNull @JsonProperty("url") String url) {
      this.url = url;
    }

    /**
     * Representation of a texture type.
     */
    public enum Type {
      SKIN,
      CAPE
    }

    /**
     * Returns the CDN url to this texture.
     * @return the url
     */
    public String getUrl() {
      return url;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Texture)) {
        return false;
      }
      Texture texture = (Texture) o;
      return Objects.equals(url, texture.url);
    }

    @Override
    public int hashCode() {
      return Objects.hash(url);
    }

    @Override
    public String toString() {
      return "Texture{" +
          "url='" + url + '\'' +
          '}';
    }
  }

  /**
   * Returns the timestamp at which those textures where made
   * @return the timestamp
   */
  public Instant getTimestamp() {
    return timestamp;
  }

  /**
   * Returns the id of the user which owns those textures
   * @return the profile id
   */
  public String getProfileId() {
    return profileId;
  }

  /**
   * Returns the name of the user which owns those textures
   * @return the profile name
   */
  public String getProfileName() {
    return profileName;
  }

  /**
   * Whether or not the signature is required.
   * @return whether or not the signature is required
   */
  public boolean isSignatureRequired() {
    return signatureRequired;
  }

  /**
   * Returns a {@link Map} containing the textures by {@link Type}.
   * @return the textures map
   */
  public Map<Type, Texture> getTextures() {
    return textures;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Textures)) {
      return false;
    }
    Textures textures1 = (Textures) o;
    return signatureRequired == textures1.signatureRequired && Objects
        .equals(timestamp, textures1.timestamp) && Objects
        .equals(profileId, textures1.profileId) && Objects
        .equals(profileName, textures1.profileName) && Objects
        .equals(textures, textures1.textures);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, profileId, profileName, signatureRequired, textures);
  }

  @Override
  public String toString() {
    return "Textures{" +
        "timestamp=" + timestamp +
        ", profileId='" + profileId + '\'' +
        ", profileName='" + profileName + '\'' +
        ", signatureRequired=" + signatureRequired +
        ", textures=" + textures +
        '}';
  }
}
