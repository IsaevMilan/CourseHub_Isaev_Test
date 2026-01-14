plugins {
    id("java-library")
    kotlin("jvm") version "1.9.20"
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {

    implementation(libs.coroutines.core)
}
