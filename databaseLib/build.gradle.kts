plugins {
    id(Plugin.App.baseLibrary)
    kotlin(Plugin.Kotlin.kapt)
    id(Plugin.Di.hilt)
}

dependencies {

    implementation(Libs.Di.hilt)
    kapt(Libs.Di.hiltCompiler)
    implementation(Libs.Database.room)
    kapt(Libs.Database.roomCompiler)
    implementation(Libs.Database.roomKtx)
}