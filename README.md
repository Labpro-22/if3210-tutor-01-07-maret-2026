# Branch 01 — Basic Views

> Part of the [IF3210 Mobile App Development — Tutorial 1](../../tree/main) series.

This branch introduces the four most common Android widgets and shows how to wire them to Kotlin code.

---

## What You'll Learn

| Concept | Description |
|---------|-------------|
| `TextView` | Display read-only text on screen |
| `EditText` | Accept text input from the user |
| `Button` | Trigger an action when tapped |
| `ImageView` | Display a drawable or bitmap |
| `setContentView()` | Load an XML layout into an Activity |
| `findViewById<T>()` | Look up a view by its `android:id` |
| `setOnClickListener {}` | React to button taps with a Kotlin lambda |

---

## Key Files

```
app/src/main/
├── java/com/example/tutor/
│   └── MainActivity.kt          ← all view wiring and click logic
└── res/
    ├── layout/
    │   └── activity_main.xml    ← XML layout with all four widgets
    ├── values/
    │   └── strings.xml          ← string resources (R.string.*)
    └── drawable/
        └── ic_launcher_foreground.xml
```

---

## Demo Features

1. **Greeting** — type a name in `EditText`, tap **Greet** → `TextView` updates to `"Hello, <name>!"`
2. **Click counter** — each tap of **Count** increments a number shown in a `TextView`
3. **ImageView** — displays the launcher icon using `setImageResource(R.drawable.*)`

---

## How It Works (MainActivity.kt)

```kotlin
// 1. Load the XML layout
setContentView(R.layout.activity_main)

// 2. Get references to views by ID
val tvGreeting: TextView = findViewById(R.id.tvGreeting)
val etName: EditText     = findViewById(R.id.etName)
val btnGreet: Button     = findViewById(R.id.btnGreet)

// 3. React to a tap
btnGreet.setOnClickListener {
    val name = etName.text.toString().trim()
    tvGreeting.text = if (name.isNotEmpty()) "Hello, $name!" else "Hello, stranger!"
}
```

---

## Things to Try

- Change the default greeting text in `res/values/strings.xml`
- Add a **Clear** button that resets both the name field and the greeting
- Make the counter show a different message when it reaches 10

---

## Next Branch

`02-layouts` — learn how **LinearLayout**, **FrameLayout**, and **ConstraintLayout** arrange views on screen.

```bash
git checkout 02-layouts
```
