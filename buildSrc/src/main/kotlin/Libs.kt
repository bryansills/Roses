object Libs {
    // Buildscript
    const val gradle_versions_plugin = "com.github.ben-manes:gradle-versions-plugin:" + Versions.gradle_versions_plugin
    const val android_gradle_plugin = "com.android.tools.build:gradle:" + Versions.android_gradle_plugin
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:" + Versions.kotlin
    const val navigation_safe_args_gradle_plugin = "android.arch.navigation:navigation-safe-args-gradle-plugin:" + Versions.navigation

    // Core
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:" + Versions.kotlin
    const val dagger = "com.google.dagger:dagger:" + Versions.dagger
    const val dagger_compiler = "com.google.dagger:dagger-compiler:" + Versions.dagger
    const val rxjava = "io.reactivex.rxjava2:rxjava:" + Versions.rxjava
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:" + Versions.rxandroid

    // Android
    const val room_runtime = "androidx.room:room-runtime:" + Versions.room
    const val room_rxjava = "androidx.room:room-rxjava2:" + Versions.room
    const val room_compiler = "androidx.room:room-compiler:" + Versions.room

    // Square
    const val moshi = "com.squareup.moshi:moshi:" + Versions.moshi
    const val moshi_codegen = "com.squareup.moshi:moshi-kotlin-codegen:" + Versions.moshi
    const val retrofit = "com.squareup.retrofit2:retrofit:" + Versions.retrofit
    const val retrofit_rxjava_adapter = "com.squareup.retrofit2:adapter-rxjava2:" + Versions.retrofit
    const val retrofit_moshi_converter = "com.squareup.retrofit2:converter-moshi:" + Versions.retrofit

    // Testing
    const val android_test_core = "androidx.test:core:" + Versions.android_test_core
    const val android_arch_core_testing = "androidx.arch.core:core-testing:" + Versions.android_arch_core_testing
    const val android_testing_support_library = "androidx.test:runner:" + Versions.android_testing_support_library
    const val rxkotlin = "io.reactivex.rxjava2:rxkotlin:" + Versions.rxkotlin

    // etc.
    const val jsoup = "org.jsoup:jsoup:" + Versions.jsoup
}