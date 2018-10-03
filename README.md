# OneSky Gradle plugin

Gradle Plugin for [OneSky](https://www.oneskyapp.com/)

## Tasks

The below tasks are added when you apply this plugin.

```
Translation tasks
-----------------
downloadTranslation - Download specified translation files (values-*/strings.xml)
showTranslationProgress - Show translation progress
uploadTranslation - Upload the default translation file (values/strings.xml)
```


## Installation

```groovy
// build.gradle
buildscript {
    dependencies {
        classpath 'com.cookpad.android:onesky-gradle-plugin:<latest-version>'
    }
}
```

```groovy
// app/build.gradle
apply plugin: 'com.cookpad.android.onesky.plugin'
onesky {
    apiKey "<api-key>"
    apiSecret "<api-secret>"
    projectId <project_id>
}
```
