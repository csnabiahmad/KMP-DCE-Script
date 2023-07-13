val ktor_version: String by project
val kotlin_version: String by project
val coroutines_version: String = "1.6.4"
plugins {
    kotlin("jvm") version "1.8.21"
    application
}

group = "com.taleemabad"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-okhttp:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson:$ktor_version")
    implementation("io.github.techgnious:IVCompressor:2.0.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

}