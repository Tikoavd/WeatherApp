@file:Suppress("UnstableApiUsage")

plugins {
    id(Dependencies.Plugins.application)
    id(Dependencies.Plugins.android)
    id(Dependencies.Plugins.kapt)
    id(Dependencies.Plugins.hilt)
}

android {
    namespace = Dependencies.Configs.namespace
    compileSdk = Dependencies.Configs.compileSdk

    defaultConfig {
        applicationId = Dependencies.Configs.applicationId
        minSdk = Dependencies.Configs.minSdk
        targetSdk = Dependencies.Configs.targetSdk
        versionCode = Dependencies.Configs.versionCode
        versionName = Dependencies.Configs.versionName

        testInstrumentationRunner = Dependencies.Configs.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        release {
            isMinifyEnabled = true
            buildConfigField(
                "String",
                "BASE_URL",
                "\"http://api.weatherapi.com/v1/\""
            )
            buildConfigField(
                "String",
                "API_KEY",
                "\"931698d476524e9ab18125819231603\""
            )
        }

        debug {
            isMinifyEnabled = false
            buildConfigField(
                "String",
                "BASE_URL",
                "\"http://api.weatherapi.com/v1/\""
            )
            buildConfigField(
                "String",
                "API_KEY",
                "\"931698d476524e9ab18125819231603\""
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Dependencies.Configs.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Configs.kotlinCompilerExtensionVersion
    }
    packagingOptions {
        resources {
            excludes.add("META-INF/LICENSE.md")
            excludes.add("META-INF/LICENSE-notice.md")
            excludes.add("META-INF/gradle/incremental.annotation.processors")
            excludes += Dependencies.Configs.excludes
        }
    }
}

dependencies {
    //coroutines
    implementation(Dependencies.Coroutines.coroutinesAndroid)

    //room
    implementation(Dependencies.Room.room)
    annotationProcessor(Dependencies.Room.annotationProcessor)
    kapt(Dependencies.Room.kapt)
    implementation(Dependencies.Room.roomCoroutines)

    //hilt
    implementation(Dependencies.Hilt.hiltAndroid)
    kapt(Dependencies.Hilt.hiltCompiler)
    implementation(Dependencies.Hilt.hiltNavigationCompose)

    //retrofit
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.gsonConverter)
    implementation(Dependencies.Retrofit.okHttp)

    //compose
    implementation(Dependencies.Compose.viewModelKtx)
    implementation(Dependencies.Compose.viewModelCompose)
    implementation(Dependencies.Compose.lifecycle)
    implementation(Dependencies.Compose.composeUi)
    implementation(Dependencies.Compose.composePreview)
    implementation(Dependencies.Compose.composeMaterial)
    implementation(Dependencies.Compose.composeConstraintLayout)
    implementation(Dependencies.Compose.accompanist)

    //location
    implementation(Dependencies.Location.googleLocation)

    //coil
    implementation(Dependencies.Coil.coil)

    //Unit tests
    testImplementation(Dependencies.Test.HILT)
    testImplementation(Dependencies.Test.JUNIT)
    testImplementation(Dependencies.Test.COROUTINES)
    testImplementation(Dependencies.Test.ARCH)
    testImplementation(Dependencies.Test.TRUTH)
    testImplementation(Dependencies.Test.JUNIT_ANDROID)
    testImplementation(Dependencies.Test.CORE)
    testImplementation(Dependencies.Test.OKHTTP)
    testImplementation(Dependencies.Test.MOCK)
    testImplementation(Dependencies.Test.RUNNER)
    testImplementation(Dependencies.Test.RULE)

    // Instrumentation tests
    androidTestImplementation(Dependencies.Test.HILT)
    kaptAndroidTest(Dependencies.Test.HILT_COMPILER)
    androidTestImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.Test.COROUTINES)
    androidTestImplementation(Dependencies.Test.ARCH)
    androidTestImplementation(Dependencies.Test.TRUTH)
    androidTestImplementation(Dependencies.Test.JUNIT_ANDROID)
    androidTestImplementation(Dependencies.Test.CORE)
    androidTestImplementation(Dependencies.Test.OKHTTP)
    androidTestImplementation(Dependencies.Test.MOCK)
    androidTestImplementation(Dependencies.Test.RUNNER)
    androidTestImplementation(Dependencies.Test.RULE)

    implementation(Dependencies.Deps.androidCore)
    implementation(Dependencies.Deps.activityCompose)
    testImplementation(Dependencies.Deps.junit)
    androidTestImplementation(Dependencies.Deps.testJunit)
    androidTestImplementation(Dependencies.Deps.espresso)
    androidTestImplementation(Dependencies.Deps.testCompose)
    debugImplementation(Dependencies.Deps.toolingCompose)
    debugImplementation(Dependencies.Deps.testManifestCompose)
}