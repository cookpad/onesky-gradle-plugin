# OneSky Gradle plugin

[ ![Download](https://api.bintray.com/packages/cookpad-inc/maven/onesky-gradle-plugin/images/download.svg) ](https://bintray.com/cookpad-inc/maven/onesky-gradle-plugin/_latestVersion)

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

# License
MIT