# MyFitnessPal clone with Compose for Wear OS

This is a jetpack compose sample app written in kotlin.

The purpose of this app is to showcase:

- Working with Jetpack Compose for Wear OS, to create beautiful UIs using the power of compose
- working with separate modules for handheld and wear os device
- working
  with [Glance](https://android-developers.googleblog.com/2022/01/announcing-glance-tiles-for-wear-os.html)
  for Wear OS to create Tiles UI
- working with Data layer APIs to connect phone apps and wear os apps so that data from these
  devices can be synced seamlessly

## Screenshots

<img src = "https://user-images.githubusercontent.com/111345322/196186237-13d51718-9db3-475d-8a5c-43bacb9ae1d6.gif" alt="home_screen" height = 250> <img width="250" alt="calories_track" src="https://user-images.githubusercontent.com/111345322/196179357-bb760e38-cff3-4e4c-99aa-048e52503215.png"> <img width="250" alt="water_Add" src="https://user-images.githubusercontent.com/111345322/196179402-fccb4334-5295-4814-a918-075c6106df32.png"> <img width="250" alt="summary" src="https://user-images.githubusercontent.com/111345322/196179416-f6ca92c3-4286-4a65-ad2e-0e091dd27efd.png"> <img width="250" alt="calories_add" src="https://user-images.githubusercontent.com/111345322/196179426-25a266fb-4e96-4734-a1a1-69caf032b25f.png"> <img width="250" alt="meals" src="https://user-images.githubusercontent.com/111345322/196179460-61b0626b-6efb-48e4-9ffb-d2d6f64d5cd8.png"> 
<img width="250" alt="Screenshot 2022-10-17 at 5 27 18 PM" src="https://user-images.githubusercontent.com/111345322/196186645-e35e98ab-d3de-46dd-bdcb-538928129f3c.png"> <img width="250" alt="dialog_confirmation" src="https://user-images.githubusercontent.com/111345322/196179475-ac41ba1e-5dd6-4d43-89e6-5687b4119f4e.png">

### Work in Progress, Tile UI:

<img width="250" alt="tile" src="https://user-images.githubusercontent.com/111345322/196179495-c56c4a2c-c3ed-42a7-8da0-8649e12d1aef.png">

## TODOs

- ~~use colors from theme~~
- Sync data with tiles
- create custom shapes and CircularProgressIndicator with Glance
- ~~refactor and clean up data layer functions in viewmodels~~
- fix crashes on mobile app
- show numbers only keyboard in wear os application
- fix bugs in syncing data items (sometimes calories won't sync)

## Tech Stack

|Tools | Link|
|---|---|
|🤖 Kotlin|[Kotlin](https://kotlinlang.org/)|
|💚 Jetpack Compose|[Compose](https://developer.android.com/jetpack/compose)|
|🌊 Coroutines|[Kotlin Coroutines](https://developer.android.com/kotlin/coroutines)|
|💚 Compose for Wear OS|[Compose Wear OS](https://developer.android.com/training/wearables/compose)|
|⌚️ Glance for Tiles|[Wear Os Tiles with Glance](https://developer.android.com/jetpack/androidx/releases/glance)|