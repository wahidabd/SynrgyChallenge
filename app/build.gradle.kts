plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("org.jlleitschuh.gradle.ktlint")
    id("com.google.gms.google-services")
    id("com.google.firebase.firebase-perf")
    id("com.google.firebase.crashlytics")
}

private val majorVersion = 0
private val minorVersion = 0
private val patchVersion = 1
private val preRelease = "Alpha"
private val release = ""

android {
    namespace = "com.wahidabd.synrgy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.wahidabd.synrgy"
        minSdk = 24
        targetSdk = 34
        versionCode = generateVersionCode()
        versionName = generateVersionName()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
        }
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions.add("env")
    productFlavors{
        create("dev"){
            dimension = "env"
            applicationIdSuffix = ".dev"
        }
        create("staging"){
            dimension = "env"
            applicationIdSuffix = ".staging"
        }
        create("production"){
            dimension = "env"
            applicationIdSuffix = ""
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

fun generateVersionCode(): Int {
    return majorVersion * 10000 + minorVersion * 100 + patchVersion
}

fun generateVersionName(): String {
    var versionName = "$majorVersion.$minorVersion.$patchVersion"
    if (preRelease.isNotEmpty()) {
        versionName = "$versionName-$preRelease"
    }
    return versionName
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension>{
    android.set(true)
    ignoreFailures.set(true)
    outputToConsole.set(true)
    outputColorName.set("RED")
    disabledRules.set(setOf(""))
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
}

dependencies {

    implementation(project(":di"))
    implementation(project(":common"))
    implementation(project(":domain"))
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-perf")

    // test
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // work manager
    implementation("androidx.work:work-runtime-ktx:2.8.1")

    // Mockito
    testApi("org.mockito:mockito-core:4.4.0")
    testApi("org.mockito:mockito-inline:4.4.0")

    // Testing
    testApi("androidx.arch.core:core-testing:2.2.0")
    testApi("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
}