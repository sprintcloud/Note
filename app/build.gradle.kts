plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "cn.ckai.note"
    compileSdk = 35

    defaultConfig {
        applicationId = "cn.ckai.note"
        minSdk = 34
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation (libs.core)
    implementation (libs.appcompat)
    implementation (libs.material)
    implementation (libs.constraintlayout)

    // Navigation Component
    implementation (libs.navigation.fragment)
    implementation (libs.navigation.ui)

    // Room
    implementation (libs.room.runtime)
    annotationProcessor (libs.room.compiler)

    // Lifecycle
    implementation (libs.lifecycle.viewmodel)
    implementation (libs.lifecycle.livedata)

    // RecyclerView
    implementation (libs.recyclerview)

    testImplementation (libs.junit)
    androidTestImplementation (libs.ext.junit)
    androidTestImplementation (libs.espresso.core)
}