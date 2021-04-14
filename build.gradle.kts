import java.util.Base64

plugins {
    java
    kotlin("jvm") version "1.4.32"
    `maven-publish`
    signing
}

group = "dev.schlaubi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(kotlin("stdlib-jdk8"))

    implementation("com.fasterxml.jackson.core", "jackson-databind", "2.12.2")
    implementation("com.squareup.retrofit2", "retrofit", "2.9.0")
    implementation("com.squareup.retrofit2", "converter-jackson", "2.9.0")
    implementation("org.jetbrains:annotations:20.1.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

kotlin {
    explicitApi()
}

val cmp = components

tasks {
    test {
        useJUnitPlatform()
    }

    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }

    val javadocJar by creating(Jar::class) {
        archiveClassifier.set("javadoc")
        from(javadoc)
    }

    publishing {
        publications {
            create<MavenPublication>(name) {
                from(cmp["java"])
                groupId = group.toString()
                artifactId = name
                version = project.version.toString()

                artifact(sourcesJar)
                artifact(javadocJar)

                pom {
                    name.set(project.name)
                    description.set("A Java wrapper of the Mojang API")

                    organization {
                        name.set("Schlaubi")
                        url.set("https://github.com/DRSchlaubi")
                    }

                    developers {
                        developer {
                            name.set("Michael Rittmeister")
                        }
                    }

                    issueManagement {
                        system.set("GitHub")
                        url.set("github.com/DRSchlaubi/mojang_api/issues")
                    }

                    licenses {
                        license {
                            name.set("MIT")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    scm {
                        connection.set("scm:git:ssh://github.com/DRSchlaubi/mojang_api.git")
                        developerConnection.set("scm:git:ssh://git@github.com:DRSchlaubi/mojang_api.git")
                    }
                }
            }
        }

        repositories {
            maven {
                setUrl("https://schlaubi.jfrog.io/artifactory/mojang_api")

                credentials {
                    username = System.getenv("ARTIFACTORY_USER")
                    password = System.getenv("ARTIFACTORY_PASSWORD")
                }
            }
        }
    }

    signing {
        val signingKey = findProperty("signingKey")?.toString()
        val signingPassword = findProperty("signingPassword")?.toString()
        if (signingKey != null && signingPassword != null) {
            useInMemoryPgpKeys(String(Base64.getDecoder().decode(signingKey.toByteArray())), signingPassword)
        }

        sign(publishing.publications[name])
    }
}
