import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
    implementation("org.hexworks.zircon:zircon.core-jvm:2020.2.0-RELEASE")
    implementation("org.hexworks.zircon:zircon.jvm.swing:2020.2.0-RELEASE")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0")
    implementation("org.hexworks.amethyst:amethyst.core-jvm:020.1.1-RELEASE")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}