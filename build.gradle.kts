// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.0")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.5")
    }
}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "8.1.1" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.google.firebase.firebase-perf") version "1.4.2" apply false
}