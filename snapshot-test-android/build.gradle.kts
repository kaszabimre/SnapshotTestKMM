plugins {
    alias(libs.plugins.android.library)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
    id(libs.plugins.paparazzi.get().pluginId)
}

android {
    namespace = "tech.apter.androidsnapshottest"
    compileSdk = libs.versions.targetSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(project(":shared-android-preview"))
    implementation(libs.compose.ui)
    implementation(libs.compose.activity)
    implementation(libs.compose.tooling)
    implementation(libs.test.showkase)
    ksp(libs.test.showkase.processor)

    testImplementation(libs.test.junit)
    testImplementation(libs.test.showkase.screenshot.testing)
    testImplementation(libs.test.param.injector)
    kspTest(libs.test.showkase.processor)
}