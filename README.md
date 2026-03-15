# Branch 02 — Layouts

> Part of the [IF3210 Mobile App Development — Tutorial 1](../../tree/main) series.

This branch demonstrates the three most important Android layout containers and when to use each one.

---

## What You'll Learn

| Layout | Description |
|--------|-------------|
| `LinearLayout` | Stack views in a single row or column; use `layout_weight` to distribute space |
| `FrameLayout` | Layer views on top of each other; useful for overlays and fragments |
| `ConstraintLayout` | Position views relative to each other or the parent; zero nesting needed |

---

## Key Files

```
app/src/main/
├── java/com/example/tutor/
│   └── MainActivity.kt
└── res/layout/
    ├── activity_main.xml        ← ConstraintLayout root with tab buttons
    ├── fragment_linear.xml      ← LinearLayout demo
    ├── fragment_frame.xml       ← FrameLayout demo
    └── fragment_constraint.xml  ← ConstraintLayout demo
```

---

## LinearLayout — Stack & Weight

```xml
<LinearLayout
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- layout_weight="1" means "take an equal share of remaining space" -->
    <Button android:layout_weight="1" ... />
    <Button android:layout_weight="2" ... />   <!-- twice as tall -->
</LinearLayout>
```

**Use when:** you have a simple list of views all going the same direction.

---

## FrameLayout — Layering

```xml
<FrameLayout ...>
    <ImageView ... />               <!-- bottom layer -->
    <TextView
        android:gravity="center"    <!-- centered on top of the image -->
        ... />
</FrameLayout>
```

**Use when:** you need to overlay views (badge on icon, loading spinner over content).

---

## ConstraintLayout — Relative Positioning

```xml
<androidx.constraintlayout.widget.ConstraintLayout ...>
    <Button
        android:id="@+id/btnA"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent" ... />

    <TextView
        <!-- place this view directly below btnA -->
        app:layout_constraintTop_toBottomOf="@id/btnA" ... />
</androidx.constraintlayout.widget.ConstraintLayout>
```

**Use when:** you have a complex UI and want to avoid deep nesting. This is the default layout in new Android Studio projects.

---

## Things to Try

- In the LinearLayout demo, change `layout_weight` values and observe how space is redistributed
- In the FrameLayout demo, add a semi-transparent colored `View` over the image
- In the ConstraintLayout demo, add a new `TextView` and chain it to an existing view

---

## Next Branch

`03-resources` — learn how `strings.xml`, `dimens.xml`, and the `R` class keep your code clean and localisation-ready.

```bash
git checkout 03-resources
```
