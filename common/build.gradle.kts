plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.wahidabd.common"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    api("androidx.core:core-ktx:1.12.0")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.10.0")
    api("androidx.constraintlayout:constraintlayout:2.1.4")
    api("androidx.navigation:navigation-fragment-ktx:2.7.5")
    api("androidx.navigation:navigation-ui-ktx:2.7.5")

    api("com.github.bumptech.glide:glide:4.16.0")
    api("de.hdodenhof:circleimageview:3.1.0")

    // Gson
    implementation("com.google.code.gson:gson:2.9.0")

    // ktor client
    api("io.ktor:ktor-client-android:2.3.4")
    api("io.ktor:ktor-client-serialization:2.3.4")
    api("io.ktor:ktor-client-logging-jvm:2.3.4")
    api("io.ktor:ktor-client-content-negotiation:2.0.0")
    api("io.ktor:ktor-serialization-kotlinx-json:2.0.0")

}