pluginManagement {
    repositories {
        maven {
            setUrl("https://maven.myket.ir")
        }
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            setUrl("https://maven.myket.ir")
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "GoalOrPooch-compose"
include(":app")
 