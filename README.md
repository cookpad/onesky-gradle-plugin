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

## Upload `values` files

```bash
./gradlew uploadTranslation
```

it will execute upload of files from multiple threads (reduce time needed for upload)

## Download translations

```bash
./gradlew downloadTranslation
```

Download translations for all specified locales. Plugin no more supporting automatic locales finding.
Download started in multi-thread mode, to economy time.

## CIS Automation for integrators

1. Developer do all strings modifications in `values` folder
2. CIS call `uploadTranslation` on each commit/build
3. When needed update of translations, developer do proper configuration of `locales` plugin property/extension:

    ```
    onesky {
        locales = ['en', 'ca', 'de', 'es', 'fi', 'fr', 'it', 'nl', 'nb', 'no', 'sv', 'da']
    }
    ```

4. Execute `downloadTranslation`
5. developer merge downloaded translations manually, resolving conflicts if needed.

> NOTE: 'en' locale allows to prevent modification of strings defined by developer, that
> greatly reduce risks of merge conflicts.
