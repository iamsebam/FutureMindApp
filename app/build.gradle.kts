plugins {
    id(Plugin.Android.application)
    kotlin(Plugin.Kotlin.android)
    kotlin(Plugin.Kotlin.kapt)
    id(Plugin.Di.hilt)
}

android {
    compileSdk = Versions.App.sdkVersion

    defaultConfig {
        applicationId = Versions.App.applicationId
        minSdk = Versions.App.minSdk
        targetSdk = Versions.App.sdkVersion
        versionCode = Versions.App.versionCode
        versionName = Versions.App.versionName
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {

    implementation(Libs.Android.coreKtx)
    implementation(Libs.Android.appCompat)
    implementation(Libs.Android.material)
    implementation(Libs.Android.constraintLayout)
    implementation(Libs.Di.hilt)
    kapt(Libs.Di.hiltCompiler)
    testImplementation(Libs.Test.junit)
}

apply(plugin = Plugin.Tools.ktLint)