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
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
    }
    apply(from = "https://raw.githubusercontent.com/JakeWharton/SdkSearch/master/gradle/projectDependencyGraph.gradle")
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