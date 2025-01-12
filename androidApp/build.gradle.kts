plugins {
    alias(libs.plugins.android.application)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
}

android {
    namespace = "tech.apter.coloredshadow.android"
    compileSdk = libs.versions.targetSdk.get().toInt()
    defaultConfig {
        applicationId = "tech.apter.coloredshadow.android"
        minSdk = libs.versions.minSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    @Suppress("UnstableApiUsage")
    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.valueOf(
            "VERSION_" + libs.versions.javaSourceCompatibility.get().replace(".", "_")
        )
        targetCompatibility = JavaVersion.valueOf(
            "VERSION_" + libs.versions.javaTargetCompatibility.get().replace(".", "_")
        )
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.javaTargetCompatibility.get().toInt()))
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":snapshot-test-android"))
    implementation(libs.compose.ui)
    implementation(libs.compose.tooling)
    implementation(libs.compose.preview)
    implementation(libs.compose.material)
    implementation(libs.compose.activity)
}