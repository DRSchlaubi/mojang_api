package dev.schlaubi.mojang_api.utils;

import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * An implementation of the Lazy pattern.
 * @param <T> the type of the value this produces
 */
public class Lazy<T> {

  private final Supplier<T> supplier;
  private T storage;

  Lazy(Supplier<T> supplier) {
    this.supplier = supplier;
  }

  /**
   * Creates a new Lazy instance
   * @param supplier the {@link Supplier} to produce the value
   * @param <T> the type of the value
   * @return a Lazy instance
   */
  @NotNull
  @Contract(value = "_ -> new", pure = true)
  public static <T> Lazy<T> forSupplier(Supplier<T> supplier) {
    return new Lazy<>(supplier);
  }

  /**
   * This creates the value if necessary and returns it.
   * @return the value
   */
  @NotNull
  public T get() {
    if (storage == null) {
      storage = supplier.get();
    }

    return storage;
  }
}
