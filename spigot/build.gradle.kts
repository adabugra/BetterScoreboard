plugins {
    java
    `java-library`
    `maven-publish`
}

dependencies {
    api(project(":core"))
    api(libs.bstats)
    compileOnly(libs.pe.spigot)
    compileOnly(libs.spigot)
    compileOnly(libs.betterreload)
    compileOnly(libs.papi)
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
