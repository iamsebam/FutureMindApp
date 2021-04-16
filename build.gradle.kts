buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(Libs.Tools.gradleBuildTools)
        classpath(Libs.Tools.gradlePlugin)
        classpath(Libs.Tools.ktLint)
        classpath(Libs.Di.hiltGradlePlugin)
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}