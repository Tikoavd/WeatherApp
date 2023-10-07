buildscript {

    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id(Dependencies.Plugins.application) version Dependencies.Versions.pluginApplication apply false
    id(Dependencies.Plugins.library) version Dependencies.Versions.pluginApplication apply false
    id(Dependencies.Plugins.android) version Dependencies.Versions.pluginAndroid apply false
    id(Dependencies.Plugins.hilt) version Dependencies.Versions.hilt apply false
}