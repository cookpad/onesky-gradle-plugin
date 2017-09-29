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
        classpath 'rejasupotaro:onesky-gradle-plugin:<latest-version>'
    }
}
```

```groovy
// app/build.gradle
apply plugin: 'rejasupotaro.onesky.plugin'
onesky {
    apiKey "<api-key>"
    apiSecret "<api-secret>"
    projectId <project_id>
}
```


## Compilation

On first run you should disable `:app` project - This is a sample of plugin use.

Open `settings.gradle` and comment line `include ':app'`

Publish SNAPSHOT version of the PLUGIN to local repository:

```bash
./gradlew uploadArchives
```

Now you can uncomment line in `settings.gradle` and run project as usual.