plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.mytodolist"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.mytodolist"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures{
        dataBinding = true;
    }
}

dependencies {

    val lifecycle_version = "2.9.3"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycle_version}")
// ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${lifecycle_version}")
    val room_version = "2.7.2"
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${lifecycle_version}")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:${lifecycle_version}")

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    implementation("androidx.recyclerview:recyclerview:1.4.0")
    // For control over item selection of both touch and mouse driven selection
    implementation("androidx.recyclerview:recyclerview-selection:1.2.0")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}