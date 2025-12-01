plugins {
    kotlin("kapt")

    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.com.google.devtools.kts)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.navigation.safeargs)
}

android {
    namespace = "com.oglcnkrty.todo_app"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.oglcnkrty.todo_app"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.roomdb)
    implementation(libs.roomKtx)
    implementation(libs.hilt)
    implementation(libs.coroutines)
    implementation(libs.navigationFragment)
    implementation(libs.navigationUi)
    implementation(libs.viewmodel)
    implementation(libs.livedata)
    implementation(libs.lifecycle)
    implementation(libs.savedsatateViewmodel)

    ksp(libs.roomdbCompiler)
    ksp(libs.hiltCompiler)

    kapt(libs.lifecycleCompiler)

    testImplementation(libs.junit)
}
kapt {
    correctErrorTypes = true
}