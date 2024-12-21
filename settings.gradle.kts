pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        gradlePluginPortal()
    }
}

rootProject.name = "better-scoreboard"

include(":spigot")
include(":core")
include("fabric")
