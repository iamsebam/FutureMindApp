plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = Versions.App.sdkVersion

    defaultConfig {
        minSdk = Versions.App.minSdk
        targetSdk = Versions.App.sdkVersion
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

apply(plugin = Plugin.Tools.ktLint)