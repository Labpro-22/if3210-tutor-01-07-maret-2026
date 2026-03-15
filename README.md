# IF3210 — Mobile App Development
## Tutorial 1 · 7 Maret 2026

Android basics tutorial project for university lectures.
Each Git branch is a **self-contained, compilable snapshot** covering one topic.

---

## Branches

| Branch | Topic | Key Concepts |
|--------|-------|--------------|
| [`main`](../../tree/main) | Project scaffold | Shared Gradle config, themes, drawables |
| [`01-basic-views`](../../tree/01-basic-views) | Basic Views | TextView, EditText, Button, ImageView, `findViewById`, click listener |
| [`02-layouts`](../../tree/02-layouts) | Layouts | LinearLayout, FrameLayout, ConstraintLayout |
| [`03-resources`](../../tree/03-resources) | Resources | `strings.xml`, `dimens.xml`, `R` class, vector drawables |
| [`04-accessibility`](../../tree/04-accessibility) | Accessibility | `contentDescription`, `labelFor`, `announceForAccessibility`, `liveRegion` |
| [`05-activities-and-intents`](../../tree/05-activities-and-intents) | Activities & Intents | Explicit Intent + extras, implicit email Intent |
| [`06-content-provider`](../../tree/06-content-provider) | Content Provider | `ContactsContract`, RecyclerView, CardView, runtime permissions |

> Branches build on each other sequentially — each branch contains all commits from the branches before it.

---

## Quick Start

```bash
# 1. Clone the repository
git clone https://github.com/Labpro-22/if3210-tutor-01-07-maret-2026.git
cd if3210-tutor-01-07-maret-2026

# 2. Check out the topic you want to explore
git checkout 01-basic-views

# 3. Open in Android Studio → let Gradle sync → Run
```

**Requirements:**
- Android Studio Hedgehog (2023.1) or newer
- Emulator or device running API 24+

---

## Tech Stack

| Item | Version |
|------|---------|
| Language | Kotlin 1.9.22 |
| UI | XML layouts (no Jetpack Compose) |
| Min SDK | 24 |
| Target / Compile SDK | 34 |
| AGP | 8.2.2 |
| Gradle | 8.6 |
| Third-party libs | None (RecyclerView/CardView in branch 06 only) |

---

## Learning Path

```
main
 └─ 01-basic-views    ← start here
     └─ 02-layouts
         └─ 03-resources
             └─ 04-accessibility
                 └─ 05-activities-and-intents
                     └─ 06-content-provider
```

Each branch README explains **what is taught**, **which files to look at**, and **what to try next**.
