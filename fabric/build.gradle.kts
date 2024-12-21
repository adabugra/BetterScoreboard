import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.include

plugins {
    java
    id("fabric-loom")
}

val minecraftVersion = libs.versions.minecraft.get()
val yarnVersion = libs.versions.yarn.get()
val loaderVersion = libs.versions.floader.get()

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$yarnVersion:v2")
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")

    modImplementation(libs.pe.fabric)
    include(api(project(":core"))!!)
}

tasks.processResources {
    inputs.property("version", rootProject.version)

    filesMatching("fabric.mod.json") {
        expand("version" to rootProject.version)
    }
}
