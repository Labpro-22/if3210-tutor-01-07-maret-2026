# Branch 06 — Content Provider

> Part of the [IF3210 Mobile App Development — Tutorial 1](../../tree/main) series.

This branch shows how to read structured data from the system (Contacts), display it in a scrollable list, and request runtime permissions safely.

---

## What You'll Learn

| Concept | Description |
|---------|-------------|
| `ContentProvider` | Android's mechanism for sharing structured data between apps |
| `ContentResolver` | Your app's client for querying a `ContentProvider` |
| `ContactsContract` | The built-in ContentProvider for the device's contact list |
| `Cursor` | A database-style result set returned by a `ContentResolver.query()` |
| `RecyclerView` | Efficient scrollable list that recycles off-screen item views |
| `RecyclerView.Adapter` | Bridges your data list to the RecyclerView |
| `ViewHolder` | Caches view references to avoid repeated `findViewById` calls |
| `CardView` | Material card container for list items |
| Runtime permissions | Ask the user for permission at runtime (required for dangerous permissions on API 23+) |

---

## Key Files

```
app/src/main/
├── java/com/example/tutor/
│   ├── MainActivity.kt          ← permission request + ContentResolver query
│   └── ContactsAdapter.kt       ← RecyclerView Adapter + ViewHolder
└── res/layout/
    ├── activity_main.xml        ← RecyclerView
    └── item_contact.xml         ← CardView layout for one contact row
```

---

## Runtime Permissions

**Step 1 — Declare in `AndroidManifest.xml`:**
```xml
<uses-permission android:name="android.permission.READ_CONTACTS" />
```

**Step 2 — Request at runtime (API 23+):**
```kotlin
if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
        != PackageManager.PERMISSION_GRANTED) {
    ActivityCompat.requestPermissions(this,
        arrayOf(Manifest.permission.READ_CONTACTS),
        REQUEST_CODE_CONTACTS)
} else {
    loadContacts()
}
```

**Step 3 — Handle the result:**
```kotlin
override fun onRequestPermissionsResult(
    requestCode: Int, permissions: Array<String>, grantResults: IntArray
) {
    if (requestCode == REQUEST_CODE_CONTACTS &&
            grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) {
        loadContacts()
    }
}
```

---

## Reading Contacts with ContentResolver

```kotlin
val cursor = contentResolver.query(
    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,  // which table
    arrayOf(                                              // columns to fetch
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER
    ),
    null, null,                                          // no WHERE clause
    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME  // ORDER BY
)

cursor?.use {
    while (it.moveToNext()) {
        val name = it.getString(0)
        val phone = it.getString(1)
        contacts.add(Contact(name, phone))
    }
}
```

> `cursor.use {}` automatically calls `close()` when the block exits — no manual cleanup needed.

---

## RecyclerView Pattern

```
RecyclerView
  └── Adapter (ContactsAdapter)
        ├── onCreateViewHolder()  — inflate item_contact.xml → ContactViewHolder
        ├── onBindViewHolder()    — fill one row with data from contacts[position]
        └── getItemCount()        — total number of rows
```

---

## Things to Try

- Add a search bar that filters the contacts list in real time
- Show the contact's photo using `ContactsContract.Contacts.Photo`
- Handle the case where the user denies the permission — show a rationale dialog

---

## This Is the Last Branch

You've now covered the core Android fundamentals. Head back to [main](../../tree/main) to see the full course overview.

```bash
git checkout main
```
