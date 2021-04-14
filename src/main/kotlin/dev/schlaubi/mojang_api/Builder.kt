package dev.schlaubi.mojang_api

/**
 * Creates a new instance of [MojangApi] by applying the settings of [builder] to it.
 */
public fun mojangApi(builder: MojangApiBuilder.() -> Unit = {}): MojangApi =
    MojangApi.newBuilder().apply(builder).build()
