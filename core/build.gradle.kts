plugins {
    java
    `java-library`
    `maven-publish`
}

dependencies {
    api(libs.sharkbyte.scoreboard.core)
    api(libs.sharkbyte.bossbar.core)
    compileOnly(libs.pe.api)
    compileOnly(libs.adventure)
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
