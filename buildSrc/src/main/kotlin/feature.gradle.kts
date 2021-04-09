plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(Versions.App.sdkVersion)

    defaultConfig {
        minSdkVersion(Versions.App.minSdk)
        targetSdkVersion(Versions.App.sdkVersion)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {

    implementation(Libs.Android.coreKtx)
    implementation(Libs.Android.appCompat)
    implementation(Libs.Android.material)
    implementation(Libs.Android.constraintLayout)
    implementation(Libs.Android.viewModel)
    implementation(Libs.Android.liveData)

    implementation(Libs.Di.hilt)
    kapt(Libs.Di.hiltCompiler)

    implementation(Libs.Tools.coroutines)
    implementation(Libs.Tools.coil)

    testImplementation(Libs.Test.junit)
}

apply(plugin = Plugin.Tools.ktLint)