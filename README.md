# Branch 03 — Resources

> Part of the [IF3210 Mobile App Development — Tutorial 1](../../tree/main) series.

This branch shows how Android's resource system separates data from code, making apps easier to maintain and localise.

---

## What You'll Learn

| Resource | File | Description |
|----------|------|-------------|
| String resources | `res/values/strings.xml` | All user-visible text in one place; supports localisation |
| Dimension resources | `res/values/dimens.xml` | Reusable size values (dp, sp) across layouts |
| Drawable resources | `res/drawable/ic_sample.xml` | Vector drawables defined in XML |
| `R` class | auto-generated | Type-safe IDs for every resource in `res/` |

---

## Key Files

```
app/src/main/
├── java/com/example/tutor/
│   └── MainActivity.kt
└── res/
    ├── values/
    │   ├── strings.xml       ← all text lives here
    │   ├── dimens.xml        ← all sizes live here
    │   └── themes.xml
    └── drawable/
        └── ic_sample.xml     ← vector drawable example
```

---

## String Resources

**`strings.xml`**
```xml
<resources>
    <string name="app_name">Tutor</string>
    <string name="btn_greet">Say Hello</string>
    <!-- Format strings: %1$s is replaced at runtime -->
    <string name="clicked">Clicked %1$d times</string>
</resources>
```

**Kotlin** — reference via the `R` class:
```kotlin
// In XML:   android:text="@string/btn_greet"
// In Kotlin:
tvCounter.text = getString(R.string.clicked, count)
```

**Why bother?** Change the text in one file and every view using it updates automatically. Add a `res/values-id/strings.xml` and you get Bahasa Indonesia for free.

---

## Dimension Resources

**`dimens.xml`**
```xml
<resources>
    <dimen name="padding_default">16dp</dimen>
    <dimen name="text_size_body">16sp</dimen>
    <dimen name="text_size_title">24sp</dimen>
</resources>
```

**In XML layout:**
```xml
android:padding="@dimen/padding_default"
android:textSize="@dimen/text_size_body"
```

**dp vs sp:** Use `dp` for sizes/spacing (scales with screen density). Use `sp` for text (also scales with the user's font size setting).

---

## Vector Drawables

Defined in `res/drawable/*.xml` — they scale to any size without pixelating:
```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp" android:height="24dp"
    android:viewportWidth="24" android:viewportHeight="24">
    <path android:fillColor="#FF6200EE" android:pathData="M12,2L2,7l10,5 10-5-10-5z"/>
</vector>
```

---

## Things to Try

- Add a second language: create `res/values-id/strings.xml` with Indonesian translations
- Move any hardcoded `textSize` values in layouts into `dimens.xml`
- Create your own vector icon in Android Studio (Resource Manager → + → Vector Asset)

---

## Next Branch

`04-accessibility` — make your app usable for everyone with `contentDescription`, `labelFor`, and live regions.

```bash
git checkout 04-accessibility
```
