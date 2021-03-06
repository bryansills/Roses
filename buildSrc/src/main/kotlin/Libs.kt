object Libs {
    // Buildscript
    const val gradle_versions_plugin =
        "com.github.ben-manes:gradle-versions-plugin:${Versions.gradle_versions_plugin}"
    const val android_gradle_plugin =
        "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigation_safe_args_gradle_plugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    // Core
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutines_test =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_android = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val dagger_android_support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val dagger_android_compiler =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val dagger_hilt_android = "com.google.dagger:hilt-android:${Versions.dagger_hilt}"
    const val dagger_hilt_compiler = "com.google.dagger:hilt-compiler:${Versions.dagger_hilt}"
    const val dagger_hilt_gradle =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger_hilt}"

    // Android
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val androidx_core = "androidx.core:core-ktx:${Versions.ktx}"
    const val android_fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"

    const val lifecycle = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.viewmodel}"
    const val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.viewmodel}"
    const val lifecycle_viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel}"

    const val navigation_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigation_fragment_ktx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"

    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"

    const val work_manager = "androidx.work:work-runtime-ktx:${Versions.work_manager}"
    const val hilt_work = "androidx.hilt:hilt-work:${Versions.androidx_hilt}"
    const val hilt_compiler = "androidx.hilt:hilt-compiler:${Versions.androidx_hilt}"

    const val startup = "androidx.startup:startup-runtime:${Versions.startup}"

    // Square
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshi_codegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_moshi_converter =
        "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    // Testing
    const val android_test_core = "androidx.test:core:${Versions.android_test_core}"
    const val android_test_runner = "androidx.test:runner:${Versions.android_test}"
    const val fragment_testing = "androidx.fragment:fragment-testing:${Versions.fragment}"
    const val android_arch_core_testing =
        "androidx.arch.core:core-testing:${Versions.android_arch_core_testing}"
    const val android_junit_testing = "androidx.test.ext:junit:${Versions.ext_junit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val kotlin_junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"

    // etc.
    const val jsoup = "org.jsoup:jsoup:${Versions.jsoup}"
    const val ktlint_gradle_plugin =
        "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlint_gradle_plugin}"
    const val detekt_gradle_plugin =
        "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detekt_gradle_plugin}"
}
