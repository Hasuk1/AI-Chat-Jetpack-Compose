plugins {
  alias(libs.plugins.androidLibrary)
  alias(libs.plugins.jetbrainsKotlinAndroid)
  kotlin("kapt")
  alias(libs.plugins.dagerHiltAndroid)
}

android {
  namespace = "com.hassuk1.core.database"
  compileSdk = 34

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
    javaCompileOptions {
      annotationProcessorOptions {
        arguments["room.schemaLocation"] =
          "$projectDir/schemas"
      }
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

  implementation(libs.room.runtime) {
    exclude(group = "org.jetbrains", module = "annotations")
    exclude(group = "com.intellij", module = "annotations")
  }
  kapt(libs.room.compiler) {
    exclude(group = "org.jetbrains", module = "annotations")
    exclude(group = "com.intellij", module = "annotations")
  }
  implementation(libs.room.ktx)

  implementation(libs.hilt.android)
  kapt(libs.hilt.android.compiler)
  kapt(libs.androidx.hilt.compiler)
  implementation(libs.androidx.hilt.navigation.compose)

  implementation(project(":core:model"))
}

kapt {
  correctErrorTypes = true
  includeCompileClasspath = false
}

