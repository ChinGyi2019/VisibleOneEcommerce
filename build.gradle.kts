// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.com.google.devtools.ksp) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.com.hilt.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.googleGMSService) apply false
}