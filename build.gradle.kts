plugins {
    java
    `maven-publish`
    id("fabric-loom") version "1.9-SNAPSHOT" apply false
}

fun prop(prop: String) = rootProject.property(prop) as String

group = prop("group")
version = prop("version")

allprojects {
    group = prop("group")
    version = prop("version")

    repositories {
        mavenLocal()
        maven("https://repo.maven.apache.org/maven2/")
        maven("https://repo.codemc.io/repository/maven-releases/")
        maven("https://jitpack.io")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }
}
