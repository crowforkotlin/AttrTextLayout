import org.jetbrains.kotlin.gradle.plugin.extraProperties

plugins {
    alias(app.plugins.android.library)
    alias(app.plugins.android.kotlin)
    id("maven-publish")
}

android {
    namespace = "com.crow.attr.text"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(app.androidx.core.ktx)
    implementation(app.androidx.appcompat)
    implementation(app.androidx.material)
    implementation(app.androidx.activity)
    implementation(app.androidx.constraintlayout)
}