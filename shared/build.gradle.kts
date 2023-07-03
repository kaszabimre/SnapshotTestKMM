plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android ()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation("org.jetbrains.kotlinx:atomicfu:0.17.3")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.compose.ui)
            }
        }
    }

    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.javaTargetCompatibility.get().toInt()))
    }
}

android {
    namespace = "tech.apter.coloredshadow"
    compileSdk = libs.versions.targetSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.valueOf(
            "VERSION_" + libs.versions.javaSourceCompatibility.get().replace(".", "_")
        )
        targetCompatibility = JavaVersion.valueOf(
            "VERSION_" + libs.versions.javaTargetCompatibility.get().replace(".", "_")
        )
    }
}