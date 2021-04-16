plugins {
    id(Plugin.App.baseLibrary)
    kotlin(Plugin.Kotlin.kapt)
    id(Plugin.Di.hilt)
}

dependencies {

    implementation(project(Module.Lib.network))
    implementation(project(Module.Lib.common))

    implementation(Libs.Di.hilt)
    kapt(Libs.Di.hiltCompiler)
    implementation(Libs.Tools.okHttp)
    implementation(Libs.Tools.retrofit)
    implementation(Libs.Tools.gson)
    implementation(Libs.Tools.gsonConverter)
    implementation(Libs.Database.room)
    kapt(Libs.Database.roomCompiler)
    implementation(Libs.Database.roomKtx)
}