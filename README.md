# Branch 05 — Activities & Intents

> Part of the [IF3210 Mobile App Development — Tutorial 1](../../tree/main) series.

This branch introduces the Android navigation model: how Activities communicate with each other and with other apps using Intents.

---

## What You'll Learn

| Concept | Description |
|---------|-------------|
| `Activity` | A single screen in an Android app |
| Back stack | Android keeps a stack of Activities; pressing Back pops the top one |
| Explicit Intent | Start a specific Activity **in your own app** by class name |
| `putExtra` / `getStringExtra` | Pass data between Activities |
| Implicit Intent | Ask Android to find an app that can handle an action (email, maps, browser…) |
| `resolveActivity()` | Check whether any app can handle the Intent before calling `startActivity()` |

---

## Key Files

```
app/src/main/
├── java/com/example/tutor/
│   ├── MainActivity.kt          ← sends both intents
│   └── SecondActivity.kt        ← receives the explicit intent + extra
└── res/layout/
    ├── activity_main.xml
    └── activity_second.xml
```

---

## Explicit Intent — Navigate Between Screens

Use when you know **exactly which class** you want to start.

```kotlin
// MainActivity.kt
val intent = Intent(this, SecondActivity::class.java)
intent.putExtra(EXTRA_NAME, etName.text.toString().trim())
startActivity(intent)
```

```kotlin
// SecondActivity.kt — retrieve the data
val name = intent.getStringExtra(MainActivity.EXTRA_NAME) ?: "stranger"
tvWelcome.text = "Welcome, $name!"
```

**Key:** define the extra key as a `companion object` constant to avoid typos:
```kotlin
companion object {
    const val EXTRA_NAME = "com.example.tutor.EXTRA_NAME"
}
```

---

## Implicit Intent — Open an External App

Use when you want to hand off work to another app (email client, browser, camera…).

```kotlin
val intent = Intent(Intent.ACTION_SENDTO).apply {
    data = Uri.parse("mailto:")      // restricts to email apps only
    putExtra(Intent.EXTRA_EMAIL, arrayOf("lecturer@university.ac.id"))
    putExtra(Intent.EXTRA_SUBJECT, "Android Tutorial Question")
    putExtra(Intent.EXTRA_TEXT, "Hi,\n\n...")
}

// Always check first — crash-proof if no email app is installed
if (intent.resolveActivity(packageManager) != null) {
    startActivity(intent)
}
```

**Why `mailto:` not `ACTION_SEND`?** `ACTION_SEND` matches Bluetooth, messaging apps, and more. Adding `data = Uri.parse("mailto:")` filters to email clients only.

---

## How the Back Stack Works

```
[MainActivity] → startActivity() → [SecondActivity]  ← user is here
                                          ↓ Back pressed
[MainActivity]  ← Android pops SecondActivity off the stack
```

---

## Things to Try

- Add a third Activity and navigate to it from `SecondActivity`
- Pass an integer extra (using `putExtra(key, intValue)` / `getIntExtra()`) alongside the name
- Try an implicit Intent for opening a URL: `Intent(Intent.ACTION_VIEW, Uri.parse("https://..."))`

---

## Next Branch

`06-content-provider` — read the device's Contacts using `ContentProvider`, display them in a `RecyclerView`, and request runtime permissions.

```bash
git checkout 06-content-provider
```
