pluginManagement {
    repositories {
        maven {
            url = uri("https://en-mirror.ir")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            url = uri("https://en-mirror.ir")
        }
    }
}

rootProject.name = "FocusFlow"
include(":app")
 