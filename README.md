# MojangAPI

Java API wrapper for the most commonly used parts of the [Mojang API](https://wiki.vg/Mojang_API)

# Sections

- Download
- Asynchronous/Synchronous
- Kotlin support

# Download

<details open>
<summary>Gradle (Kotlin)</summary>

```kotlin
repositories {
    maven("https://schlaubi.jfrog.io/artifactory/mojang_api")
}

dependencies {
    implementation("dev.schlaubi:mojang_api:1.0.0")
}
```

</details>
<details>
<summary>Gradle (Groovy)</summary>

```groovy
repositories {
    maven { url "https://schlaubi.jfrog.io/artifactory/mojang_api" }
}

dependencies {
    implementation 'dev.schlaubi:mojang_api:1.0.0'
}
```

</details>

<details>
<summary>Maven</summary>

```xml

<project>
  <repositories>
    <repository>
      <url>https://schlaubi.jfrog.io/artifactory/mojang_api</url>
    </repository>
  </repositories>

  <dependencies>
    <dependency>
      <groupId>me.schlaubi</groupId>
      <artifactId>mojang_api</artifactId>
      <version>1.0.0</version>
    </dependency>
  </dependencies>
</project>
```

</details>

# Example

```java
public class Example {

  void doStuff() {
    var api = MojangAPI.create();

    api.getUsers().findUserByNameAsync("<id>").thenAccept(System.out::println);
  }
}
```

# Asynchronous/Synchronous

The core of the API is completely asynchronous an
uses [CompletableFuture](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/CompletableFuture.html)
you can recognize such a method by the `Async` suffix.

For all methods there also is a sync `doX` method in addition to the `doXAsync` method

# Kotlin Support

In Kotlin you have a builder DSL to create instances of the API.

```kotlin
var api = mojangApi {
    okHttpClient = client /*<my client>*/
}

// or without options
val api = mojangApi()

```

For coroutines support you should
use [kotlinx.coroutines-jdk8](https://github.com/Kotlin/kotlinx.coroutines/tree/master/integration/kotlinx-coroutines-jdk8)
