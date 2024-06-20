## Color Palette App
This app build with Compose and MVI architecture, 

### Main dependencies:
Kotlin, Flows, Compose, Hilt, Room, Retrofit.

### The app support in:
1. Dark/Light mode
2. Process death
3. Configuration change (rotate screen, change language etc,)
4. Persistency (Room)
5. Color change animation

## Notes
I tried to use the newest version of compose navigation `Version 2.8.0-beta03` but it crashes in runtime when trying to restore state after process death.
