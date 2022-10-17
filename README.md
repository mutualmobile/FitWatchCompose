# MyFitnessPal clone with Compose for Wear OS

This is a jetpack compose sample app written in kotlin.

The purpose of this app is to showcase:
   - Working with Jetpack Compose for Wear OS, to create beautiful UIs using the power of compose
   - working with separate modules for handheld and wear os device
   - working with Glance for Wear OS to create Tiles UI
   - working with Data layer APIs to connect phone apps and wear os apps so that data from these devices can be synced seamlessly
  
## Screenshots

<img src = "https://user-images.githubusercontent.com/111345322/196177251-37b72ccb-bd38-4623-b60e-cd20d7793c99.png" alt="home_screen" height = 250> <img width="250" alt="calories_track" src="https://user-images.githubusercontent.com/111345322/196179357-bb760e38-cff3-4e4c-99aa-048e52503215.png"> <img width="250" alt="water_Add" src="https://user-images.githubusercontent.com/111345322/196179402-fccb4334-5295-4814-a918-075c6106df32.png"> <img width="250" alt="summary" src="https://user-images.githubusercontent.com/111345322/196179416-f6ca92c3-4286-4a65-ad2e-0e091dd27efd.png"> <img width="250" alt="calories_add" src="https://user-images.githubusercontent.com/111345322/196179426-25a266fb-4e96-4734-a1a1-69caf032b25f.png"> <img width="250" alt="calories_add_before" src="https://user-images.githubusercontent.com/111345322/196179452-6100ec9d-c0f9-4fc9-bba9-2f9c9e9c856e.png"> <img width="250" alt="meals" src="https://user-images.githubusercontent.com/111345322/196179460-61b0626b-6efb-48e4-9ffb-d2d6f64d5cd8.png"> <img width="250" alt="dialog_confirmation" src="https://user-images.githubusercontent.com/111345322/196179475-ac41ba1e-5dd6-4d43-89e6-5687b4119f4e.png">

### Work in Progress, Tile UI:
<img width="250" alt="tile" src="https://user-images.githubusercontent.com/111345322/196179495-c56c4a2c-c3ed-42a7-8da0-8649e12d1aef.png">

## TODOs
   - use colors from theme
   - Sync data with tiles
   - create custom shapes and CircularProgressIndicator with Glance
   - refactor and clean up data layer functions in viewmodels
   - fix crashes on mobile app
   - show numbers only keyboard in wear os application
   - fix bugs in syncing data items (sometimes calories won't sync)
