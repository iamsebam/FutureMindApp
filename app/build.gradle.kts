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

    hilt {
        enableExperimentalClasspathAggregation = true
    }
}

dependencies {

    implementation(project(Module.MainFeature.feature))

    implementation(project(Module.Lib.resources))

    implementation(Libs.Di.hilt)
    kapt(Libs.Di.hiltCompiler)
    implementation(Libs.Android.navigation)
    implementation(Libs.Android.appCompat)
}

apply(plugin = Plugin.Tools.ktLint)