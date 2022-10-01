# MovieApp
[![Platform](https://img.shields.io/badge/platform-android-brightgreen)](https://developer.android.com/reference) [![API](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat)](https://developer.android.com/studio/releases/platforms#5.0) ![Sample App shield](https://img.shields.io/badge/App-Sample-green.svg)
[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.7.x-blue.svg)](https://kotlinlang.org)[![AGP](https://img.shields.io/badge/AGP-7.x-blue?style=flat)](https://developer.android.com/studio/releases/gradle-plugin)[![Gradle](https://img.shields.io/badge/Gradle-7.x-blue?style=flat)](https://gradle.org) 

 easy way to discover popular movies. This is a simple TMDb client for Android




## Built With 🛠
* [100% Kotlin](https://kotlinlang.org/)
    + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations (asynchronous and more)
    + [Kotlin Flow](https://kotlinlang.org/docs/flow.html) - data flow across all app layers, including views
    + [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html) - parse [JSON](https://www.json.org/json-en.html)
* [Retrofit](https://square.github.io/retrofit/) - networking
* [Moshi](https://github.com/square/moshi) - networking
* [OkHttp](https://square.github.io/okhttp/) - networking
 * [Jetpack](https://developer.android.com/jetpack)
    * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - in-app navigation
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when
      lifecycle state changes
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related
      data in a lifecycle-aware way
    * [Data Binding](https://developer.android.com/topic/libraries/data-binding) - bind UI components 
    * [Room](https://developer.android.com/jetpack/androidx/releases/room) - store offline cache
  * [Hilt](https://dagger.dev/hilt/) - dependency injection 
  * Modern Architecture
  * Clean Architecture (at feature module level)
  * Single activity architecture
    using [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
  * MVVM Arch
  * [Android Architecture components](https://developer.android.com/topic/libraries/architecture)
    ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    , [Kotlin Flow](https://kotlinlang.org/docs/flow.html)
    , [Navigation Graph](https://developer.android.com/guide/navigation/navigation-design-graph) - The Navigation component uses a navigation graph to manage your app's navigation.  
  * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions
  * [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - Paging with offline caching and Mediator
  * [Picasso](https://github.com/square/picasso) - image downloading and caching library for Android
  
## Architecture 🏗️
This app uses [MVVM](https://developer.android.com/topic/architecture) Architecture.

![architecture](images/android-mvvm-pattern.png)

