plugins {
    id(Plugin.Android.library)
    kotlin(Plugin.Kotlin.android)
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

dependencies {

    implementation(Libs.Android.appCompat)
    implementation(Libs.Android.material)
}