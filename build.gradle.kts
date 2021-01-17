import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21"
}

group = "me.skelo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven(url = "https://kotlin.bintray.com/kotlinx")
}

dependencies {
    implementation("org.hexworks.zircon:zircon.core-jvm:2020.2.0-RELEASE")
    implementation("org.hexworks.zircon:zircon.jvm.swing:2020.2.0-RELEASE")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}