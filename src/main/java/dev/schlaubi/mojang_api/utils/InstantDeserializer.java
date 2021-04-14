package dev.schlaubi.mojang_api.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.Instant;

/**
 * Implementation of {@link JsonDeserializer} that uses {@link Instant#ofEpochMilli(long)} to
 * deserialize {@link Instant}.
 */
public class InstantDeserializer extends JsonDeserializer<Instant> {

  @Override
  public Instant deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    return Instant.ofEpochMilli(p.getLongValue());
  }
}
