plugins {
    id(Plugin.Android.library)
    kotlin(Plugin.Kotlin.android)
    kotlin(Plugin.Kotlin.kapt)
    id(Plugin.Di.hilt)
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

    implementation(Libs.Di.hilt)
    kapt(Libs.Di.hiltCompiler)
    implementation(Libs.Database.room)
    kapt(Libs.Database.roomCompiler)
    implementation(Libs.Database.roomKtx)
    implementation(Libs.Tools.gsonConverter)
}