pluginManagement {
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "Something Calc"
include(":app")
include(":core:designsystem")
include(":core:ui")
include(":core:utility")
include(":core:logger")
include(":feature:login")
include(":feature:chat")
include(":core:navigation")
