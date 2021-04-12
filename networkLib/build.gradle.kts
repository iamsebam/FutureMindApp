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
    implementation(Libs.Tools.retrofit)
    implementation(Libs.Tools.gson)
    implementation(Libs.Tools.gsonConverter)
}