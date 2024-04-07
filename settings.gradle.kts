pluginManagement {
  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
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

rootProject.name = "AI-Chat-Jetpack-Compose"
include(":app")
include(":feature:chat")
include(":feature:authentication")
include(":core:model")
include(":core:ui")
include(":core:data")
include(":core:designsystem")
include(":core:database")
