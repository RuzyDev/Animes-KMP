plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    namespace = "br.com.arcom.apparcom.android"
    compileSdk = 35
    defaultConfig {
        applicationId = "br.com.arcom.apparcom.android"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.1"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true // Habilita o minify
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), // Configurações padrão do ProGuard
                "proguard-rules.pro" // Suas regras personalizadas
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.bundles.material3)
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.metrics)
    implementation(libs.androidx.tracing)
    implementation(libs.coil.compose)
    implementation(libs.paging.compose)
    implementation(libs.datetime)
    implementation(libs.lottie.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.accompanist.permissions)
    implementation(libs.android.material)
    implementation(libs.bundles.firebase)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.lifecycle.runtime.compose)

    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
}