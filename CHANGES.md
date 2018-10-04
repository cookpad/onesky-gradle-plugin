# Revision History of license-tools-plugin

## v0.1.1

### Breaking change
- Changed the repository from `rejasupotaro` to `com.cookpad.android`
    - new:
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
          // ...
        }
        ```
    - before:
    
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
          // ...
        }
        ```

## Enhancement

- Add uploadTranslationAndMarkAsDeprecated task [#11](https://github.com/cookpad/onesky-gradle-plugin/pull/11)

## v0.1.0

initial release
