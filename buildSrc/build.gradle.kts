plugins {
    `kotlin-dsl`
}
repositories {
    google()
    mavenCentral()
}
dependencies {
    implementation("com.android.tools.build:gradle:7.0.0-alpha14")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.33-beta")
}