plugins {
    id(Plugin.App.baseLibrary)
    kotlin(Plugin.Kotlin.kapt)
    id(Plugin.Di.hilt)
}

dependencies {

    implementation(Libs.Di.hilt)
    kapt(Libs.Di.hiltCompiler)
    implementation(Libs.Tools.okHttp)
    implementation(Libs.Tools.retrofit)
    implementation(Libs.Tools.gson)
    implementation(Libs.Tools.gsonConverter)
}