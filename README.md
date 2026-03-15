# Branch 04 — Accessibility

> Part of the [IF3210 Mobile App Development — Tutorial 1](../../tree/main) series.

This branch shows how to make your app usable by people who rely on screen readers (TalkBack) and other assistive technologies.

---

## What You'll Learn

| API | Where | Description |
|-----|-------|-------------|
| `contentDescription` | XML / Kotlin | Provides a spoken label for views that have no visible text (icons, images) |
| `labelFor` | XML | Links a `TextView` label to an `EditText` so TalkBack reads both together |
| `announceForAccessibility()` | Kotlin | Sends a live announcement to TalkBack (e.g., after an async operation) |
| `accessibilityLiveRegion` | XML | Automatically announces a `TextView` whenever its text changes |

---

## Key Files

```
app/src/main/
├── java/com/example/tutor/
│   └── MainActivity.kt          ← announceForAccessibility() demo
└── res/layout/
    └── activity_main.xml        ← contentDescription, labelFor, liveRegion examples
```

---

## contentDescription

Use for `ImageView`, icon-only `Button`, or any view without visible text:

```xml
<!-- Without this, TalkBack would say "unlabelled button" -->
<ImageButton
    android:src="@drawable/ic_send"
    android:contentDescription="@string/btn_send_desc" />
```

```xml
<!-- Purely decorative image — hide it from accessibility tree -->
<ImageView
    android:importantForAccessibility="no" />
```

---

## labelFor

Links a visible label `TextView` to the input field it describes:

```xml
<TextView
    android:id="@+id/tvNameLabel"
    android:text="@string/label_name"
    android:labelFor="@id/etName" />   <!-- points at the EditText -->

<EditText android:id="@+id/etName" ... />
```

TalkBack now reads: *"Name, Edit box"* when the user focuses the `EditText`.

---

## accessibilityLiveRegion

Auto-announces text changes without any Kotlin code:

```xml
<TextView
    android:id="@+id/tvStatus"
    android:accessibilityLiveRegion="polite" />
    <!-- "polite" waits for current speech to finish -->
    <!-- "assertive" interrupts immediately (use sparingly) -->
```

---

## announceForAccessibility

Send a one-off announcement from Kotlin (useful after an async event):

```kotlin
btnSubmit.setOnClickListener {
    // ... do work ...
    tvStatus.announceForAccessibility(getString(R.string.msg_submitted))
}
```

---

## Testing with TalkBack

1. **Settings → Accessibility → TalkBack → On**
2. Swipe right/left to move focus between views
3. Double-tap to activate the focused view
4. Verify every interactive element has a meaningful spoken label

---

## Things to Try

- Find an `ImageView` with no `contentDescription` and add one
- Switch on TalkBack and navigate the demo screen — notice where labels are missing
- Change `liveRegion` from `polite` to `assertive` and compare the behaviour

---

## Next Branch

`05-activities-and-intents` — navigate between screens with explicit Intents, and launch external apps with implicit Intents.

```bash
git checkout 05-activities-and-intents
```
