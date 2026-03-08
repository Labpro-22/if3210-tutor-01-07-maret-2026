# Android Basics Tutorial

University tutorial project demonstrating Android fundamentals, organized into numbered Git branches.

## Branches

| Branch | Topic |
|--------|-------|
| `01-basic-views` | TextView, EditText, Button, ImageView + click counter |
| `02-layouts` | LinearLayout, FrameLayout, ConstraintLayout |
| `03-resources` | strings.xml, dimens.xml, drawables, R class |
| `04-accessibility` | contentDescription, labelFor, announceForAccessibility |
| `05-activities-and-intents` | Explicit + implicit Intents, passing data |
| `06-content-provider` | ContactsContract + RecyclerView + runtime permissions |

## Setup

1. Clone the repo
2. Open in **Android Studio Hedgehog (2023.1)** or newer
3. Check out the branch you want: `git checkout 01-basic-views`
4. Let Android Studio sync Gradle
5. Run on an emulator (minSdk 24 / API 24+)

## Requirements

- minSdk 24, targetSdk 34
- Kotlin, XML layouts (no Jetpack Compose)
- AndroidX only, no third-party libraries (except RecyclerView/CardView in branch 06)
