plugins {
    id(Plugin.App.feature)
}
dependencies {
    implementation(Libs.Android.swipeRefreshLayout)
    implementation(project(Module.MainFeature.lib))
}
