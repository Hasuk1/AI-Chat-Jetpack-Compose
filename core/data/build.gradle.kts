plugins {
  alias(libs.plugins.androidLibrary)
  alias(libs.plugins.jetbrainsKotlinAndroid)
  kotlin("kapt")
  alias(libs.plugins.dagerHiltAndroid)
}

android {
  namespace = "com.hassuk1.core.data"
  compileSdk = 34

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
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

  implementation(libs.hilt.android)
  kapt(libs.hilt.android.compiler)
  kapt(libs.androidx.hilt.compiler)
  implementation(libs.androidx.hilt.navigation.compose)

  implementation(libs.retrofit)
  implementation(libs.converter.gson)
  implementation(libs.okhttp)
  implementation(libs.logging.interceptor)

  implementation(project(":core:database"))
  api(project(":core:network"))
  implementation(project(":core:model"))

}

kapt {
  correctErrorTypes = true
}