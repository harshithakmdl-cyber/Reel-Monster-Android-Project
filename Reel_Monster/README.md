# Reel Monster (Android starter)

A simple Android app for tracking short-form video scrolling. Includes:
- Manual reel counter, saved locally with SharedPreferences
- Floating monster overlay bubble (requires Android overlay permission)
- Draggable bubble, tap-to-count, playful milestone line
- Today's count and estimated time lost
- Offline use, no login or internet required

## Important limitations
This starter implements **Level 1 manual counting** and the floating overlay. Android does not let ordinary apps reliably inspect Instagram/YouTube content. Level 2 automatic counting is not implemented; the app explains the accessibility-permission path but does not read or monitor reels. The time estimate assumes 30 seconds per reel and is only a rough estimate. The seven-day chart/history is not implemented in this starter.

## Build
Open this folder in Android Studio, let Gradle sync, then Run on a real Android phone (Android 6+).
Alternatively install Android command-line tools and Gradle, then run:
`gradle assembleDebug`
The APK is normally at `app/build/outputs/apk/debug/app-debug.apk`.

## First use
1. Install and open Reel Monster.
2. Tap **Allow display over other apps** and enable it.
3. Return to app and tap **Start floating monster**.
4. Keep the bubble visible while using Instagram or YouTube. Tap it once per reel.
5. Tap **Stop floating monster** to remove it.

## GitHub
Upload the project folder contents to a repository. This is an Android project, not a GitHub Pages website. Use Android Studio to build and install it.
