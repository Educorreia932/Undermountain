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
    implementation("org.hexworks.zircon:zircon.core-jvm:$zirconVersion")
    implementation("org.hexworks.zircon:zircon.jvm.swing:$zirconVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0")
    implementation("org.hexworks.amethyst:amethyst.core-jvm:$amethystVersion")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}