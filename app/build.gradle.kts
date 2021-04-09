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
        }
    }
}

dependencies {

    implementation(project(Module.Feature.list))

    implementation(Libs.Android.material)
    implementation(Libs.Di.hilt)
    kapt(Libs.Di.hiltCompiler)
}

apply(plugin = Plugin.Tools.ktLint)