
# Post App
Application that show posts and its details from https://jsonplaceholder.typicode.com/ and allow the user to refresh and delete posts.
Include cache to works offline.
This projects is made in kotlin and use the MVVM architecture, use retrofit and coroutines to reach remote services, and room database to keep local data.

This application compile with android API 30 and supports min Android API 22.

## How to use
### Using Android Studio
1. clone the project ```git clone git@bitbucket.org:Leinaro/posts-android.git```
2. Open an existing project and select the cloned project
3. Run the application in a device or emulator.

### Using Apk
Download the [apk](https://bitbucket.org/Leinaro/posts-android/src/master/debug%20apk/app-debug.apk) in your device or drag the downloaded apk to the emulator.

## Tech
### UI
- [navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
- [viewpager2](https://developer.android.com/guide/navigation/navigation-swipe-view-2)
- [recyclerview](https://developer.android.com/guide/topics/ui/layout/recyclerview)
- [constraint layout](https://developer.android.com/training/constraint-layout)

### Architecture Components
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Room](https://developer.android.com/topic/libraries/architecture/room)

### Others
- [Coroutines](https://medium.com/androiddevelopers/easy-coroutines-in-android-viewmodelscope-25bffb605471)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Retrofit](https://square.github.io/retrofit/)
- [Arrow](https://arrow-kt.io/)
- [Android KTX](https://developer.android.com/kotlin/ktx?gclid=CjwKCAjww5r8BRB6EiwArcckC13FSHoWY_aplu6kI-_mDPMjwm9SO4MAlIH6GtqcD86jlZ85S46Q8hoCvUUQAvD_BwE&gclsrc=aw.ds)

## Files Architecture

**datasources** Contains the classes to manage the database and remote services.

**repository** Contains the classes that handle the data sources and maps the data to the viewmodel.

**ui:** Contains the view components: the activities, fragments, and its viewmodel. Also contains adapters, viewholders and handlers.

**utils** Contains a class with the class extension and the ViewHandler.

## Good practices
- SOLID
- Dependency Injection
- Clean code
