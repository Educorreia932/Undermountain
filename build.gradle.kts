import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val zirconVersion: String by project
val amethystVersion: String by project

plugins {
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.serialization") version "1.4.10"
}

repositories {
    mavenCentral()
    jcenter()

    maven(url = "https://kotlin.bintray.com/kotlinx")
}

dependencies {
    implementation("org.hexworks.zircon:zircon.core-jvm:2021.1.0-RELEASE")
    implementation("org.hexworks.zircon:zircon.jvm.swing:2021.1.0-RELEASE")
    implementation("org.hexworks.amethyst:amethyst.core-jvm:2021.0.3-RELEASE")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}