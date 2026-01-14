plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core"))

    // Retrofit нужен мне для описания Api
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
}