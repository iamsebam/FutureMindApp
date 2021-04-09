object Libs {

    object Gradle {

        const val gradleBuildTools = "com.android.tools.build:gradle:${Versions.Gradle.gradleBuildTools}"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Gradle.kotlinGradlePlugin}"
    }

    object Di {

        const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.Di.hilt}"
        const val hilt = "com.google.dagger:hilt-android:${Versions.Di.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.Di.hilt}"
    }

    object Android {

        const val coreKtx = "androidx.core:core-ktx:${Versions.Android.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.Android.appCompat}"
        const val material = "com.google.android.material:material:${Versions.Android.material}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.Android.constraintLayout}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.lifecycle}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Android.lifecycle}"
    }

    object Database {

        const val room = "androidx.room:room-runtime:${Versions.Database.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.Database.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.Database.room}"
    }

    object Tools {

        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Tools.retrofit}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Tools.coroutines}"
        const val coil = "io.coil-kt:coil:${Versions.Tools.coil}"
        const val ktLint = "org.jmailen.gradle:kotlinter-gradle:${Versions.Tools.ktLint}"
    }

    object Test {

        const val junit = "junit:junit:${Versions.Test.junit}"
    }

}