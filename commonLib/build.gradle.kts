plugins {
    id(Plugin.App.baseLibrary)
    kotlin(Plugin.Kotlin.kapt)
    id(Plugin.Di.hilt)
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(Libs.Android.lifecycleCommon)
    implementation(Libs.Android.lifecycleRuntime)
    implementation(Libs.Android.liveData)
    implementation(Libs.Android.fragmentKtx)

    implementation(Libs.Di.hilt)
    kapt(Libs.Di.hiltCompiler)
}