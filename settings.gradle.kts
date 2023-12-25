pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
        maven { setUrl("https://jitpack.io") }
    }
}


dependencyResolutionManagement {

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    versionCatalogs {
        create("compose") { from(files("gradle/compose.versions.toml")) }
        create("app") { from(files("gradle/app.versions.toml") )}
    }
    repositories {

        mavenLocal()
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { setUrl("https://plugins.gradle.org/m2/") }
        maven { setUrl("https://maven.aliyun.com/nexus/content/groups/public/") }
        maven { setUrl("https://jitpack.io") }
    }
}

rootProject.name = "AttrTextLayout"
include(":app")
include(":crow-attr-text")
