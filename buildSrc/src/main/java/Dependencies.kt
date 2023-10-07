object Dependencies {
    object Deps {
        val androidCore by lazy { "androidx.core:core-ktx:1.9.0" }
        val activityCompose by lazy { "androidx.activity:activity-compose:1.6.1" }
        val junit by lazy { "junit:junit:4.13.2" }
        val testJunit by lazy { "androidx.test.ext:junit:1.1.5" }
        val espresso by lazy { "androidx.test.espresso:espresso-core:3.5.1" }
        val testCompose by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.composeUi}" }
        val toolingCompose by lazy { "androidx.compose.ui:ui-tooling:${Versions.composeUi}" }
        val testManifestCompose by lazy { "androidx.compose.ui:ui-test-manifest:${Versions.composeUi}" }
    }

    object Test {
        // Instrumentation tests
        val HILT = "com.google.dagger:hilt-android-testing:2.37"
        val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:2.37"
        val JUNIT = "junit:junit:4.13.2"
        val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1"
        val ARCH = "androidx.arch.core:core-testing:2.1.0"
        val TRUTH = "com.google.truth:truth:1.1.3"
        val JUNIT_ANDROID = "androidx.test.ext:junit:1.1.3"
        val CORE = "androidx.test:core-ktx:1.4.0"
        val OKHTTP = "com.squareup.okhttp3:mockwebserver:4.9.1"
        val MOCK = "io.mockk:mockk-android:1.13.8"
        val RUNNER = "androidx.test:runner:1.4.0"
        val RULE = "androidx.test:rules:1.5.0"
    }

    object Room {
        private const val room_version = "2.5.2"

        val room = "androidx.room:room-runtime:$room_version"
        val annotationProcessor = "androidx.room:room-compiler:$room_version"
        val kapt = "androidx.room:room-compiler:$room_version"
        val roomCoroutines = "androidx.room:room-ktx:$room_version"
    }

    object Hilt {
        private const val hiltNavVersion = "1.0.0"

        val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
        val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }
        val hiltNavigationCompose by lazy { "androidx.hilt:hilt-navigation-compose:$hiltNavVersion" }
    }

    object Retrofit {
        private const val retrofitVersion = "2.9.0"
        private const val okHttpVersion = "4.10.0"

        val retrofit by lazy { "com.squareup.retrofit2:retrofit:$retrofitVersion" }
        val gsonConverter by lazy { "com.squareup.retrofit2:converter-gson:$retrofitVersion" }
        val okHttp by lazy { "com.squareup.okhttp3:logging-interceptor:$okHttpVersion" }
    }

    object Compose {
        private const val lifecycleVersion = "2.6.0"
        private const val materialVersion = "1.3.1"

        val viewModelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion" }
        val viewModelCompose by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion" }
        val lifecycle by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion" }
        val composeUi by lazy { "androidx.compose.ui:ui:${Versions.composeUi}" }
        val composePreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.composeUi}" }
        val composeMaterial by lazy { "androidx.compose.material:material:$materialVersion" }
        const val composeConstraintLayout =
            "androidx.constraintlayout:constraintlayout-compose:1.0.0"
        const val accompanist = "com.google.accompanist:accompanist-systemuicontroller:0.18.0"
    }

    object Coil {
        private const val coilVersion = "2.0.0-rc01"
        const val coil = "io.coil-kt:coil-compose:$coilVersion"
    }

    object Coroutines {
        private const val coroutinesVersion = "1.6.4"

        val coroutinesAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion" }
    }

    object Location {
        const val googleLocation = "com.google.android.gms:play-services-location:19.0.1"
    }

    object Plugins {
        const val application = "com.android.application"
        const val library = "com.android.library"
        const val android = "org.jetbrains.kotlin.android"
        const val kapt = "kotlin-kapt"
        const val hilt = "com.google.dagger.hilt.android"
    }

    object Versions {
        const val composeUi = "1.3.3"
        const val pluginApplication = "7.3.1"
        const val pluginAndroid = "1.8.10"
        const val hilt = "2.44"
    }

    object Configs {
        const val namespace = "com.practicework.weatherapp"
        const val applicationId = "com.practicework.weatherapp"
        const val minSdk = 23
        const val compileSdk = 33
        const val targetSdk = 33
        const val versionCode = 1
        const val versionName = "1.0"
        const val testInstrumentationRunner = "com.practicework.weatherapp.ui.homepage.di.HiltTestRunner"
        const val jvmTarget = "1.8"
        const val kotlinCompilerExtensionVersion = "1.4.3"
        const val excludes = "/META-INF/{AL2.0,LGPL2.1}"
    }
}