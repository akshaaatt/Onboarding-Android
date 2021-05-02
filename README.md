Onboarding is an Android library for easing up the onboarding process to Apps.

## Features

* Written in Kotlin
* No boilerplate code
* Easy initialisation
* Supports Lottie Animation View and Images with a Title and Description.

## Gradle Dependency

* Add the JitPack repository to your project's build.gradle file

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

* Add the dependency in your app's build.gradle file

```
dependencies {
    implementation 'com.github.akshaaatt:Onboarding:1.0.0'
}
```

## Usage

```kotlin
 val onboarderPage1 = OnboarderPage(
                title = "TITLE1",
                description = "DESC1",
                resourceId = R.drawable.res1,
                isLottie = true,
        )

        val onboarderPage2 = OnboarderPage(
                title = "TITLE2",
                description = "DESC2",
                resourceId = R.drawable.res2
        )

        val onboarderPage3 = OnboarderPage(
                "Mars",
                "Say hi to Curiosity!",
                resourceId = R.drawable.third
        )

        onboarderPage1.backgroundColor = R.color.color1
        onboarderPage2.backgroundColor = R.color.color2
        onboarderPage3.backgroundColor = R.color.color3

        val pages: MutableList<OnboarderPage> = ArrayList()
        pages.add(onboarderPage1)
        pages.add(onboarderPage2)
        pages.add(onboarderPage3)

        for (page in pages) {
            page.titleColor = R.color.primary_text
            page.descriptionColor = R.color.secondary_text
            page.isMultilineDescriptionCentered = true
        }
```

## Contribution

You are most welcome to contribute to this project!
