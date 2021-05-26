[![Screenshot-2021-05-26-23-40-20-876-com-limerse-onboarding.jpg](https://i.postimg.cc/3wK25pJJ/Screenshot-2021-05-26-23-40-20-876-com-limerse-onboarding.jpg)](https://postimg.cc/zycyTyg9)
[![ezgif-com-gif-maker-1.gif](https://i.postimg.cc/yxqFGGD2/ezgif-com-gif-maker-1.gif)](https://postimg.cc/Q9b990kq)

Onboarder is an Android library for easing up the onboarding process to Apps.

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

## Basic usage

To use Onboarder, you simply have to create a new Activity that extends OnboardAdvanced or OnboardLegacy like the following:

```kotlin
class MyCustomOnboarder : OnboardAdvanced() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure you don't call setContentView!

        // Call addSlide passing your Fragments.
        // You can use OnboardFragment to use a pre-built fragment
        addSlide(OnboardFragment.newInstance(
                title = "Welcome...",
                description = "This is the first slide of the example"
        ))
        addSlide(OnboardFragment.newInstance(
                title = "...Let's get started!",
                description = "This is the last slide, I won't annoy you more :)"
        ))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        finish()
    }
}
```

Please note that you must NOT call setContentView. The OnboardAdvanced superclass is taking care of it for you.

Also confirm that you're overriding onCreate with a single parameter (Bundle) and you're not using another override (like onCreate(Bundle, PersistableBundle)) instead.

Finally, declare the activity in your Manifest like so:

``` xml
<activity android:name="com.example.MyCustomOnboard"
    android:label="My Custom Onboard" />
```
    
We suggest to don't declare MyCustomOnboard as your first Activity unless you want the intro to launch every time your app starts. Ideally you should show the OnboardAdvanced activity only once to the user, and you should hide it once completed (you can use a flag in the SharedPreferences).

#### Inspired by [AppIntro](https://github.com/AppIntro/AppIntro)

## Contribution

You are most welcome to contribute to this project!
