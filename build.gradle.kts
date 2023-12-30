// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(app.plugins.android.application) apply false
    alias(app.plugins.android.library) apply false
    alias(app.plugins.android.kotlin) apply false
    alias(libs.plugins.mavenPublish) apply false
    `maven-publish`
}
true