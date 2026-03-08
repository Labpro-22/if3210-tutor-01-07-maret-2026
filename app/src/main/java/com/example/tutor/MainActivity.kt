package com.example.tutor

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Branch 06 — Content Provider
 *
 * A ContentProvider is Android's mechanism for sharing structured data between apps.
 * The OS ships with several built-in providers:
 *   • ContactsContract  — device contacts
 *   • MediaStore        — photos, videos, audio
 *   • CalendarContract  — calendar events
 *
 * To read data you use contentResolver.query(), which works like a SQL SELECT.
 * It returns a Cursor — an iterator over the result rows.
 *
 * Runtime permissions (Android 6.0 / API 23+):
 *   Sensitive permissions ("dangerous" group) must be granted by the user at runtime.
 *   READ_CONTACTS is one of these — we must:
 *     1. Declare it in AndroidManifest.xml (install-time)
 *     2. Check if it's already granted (ContextCompat.checkSelfPermission)
 *     3. Request it if not granted (registerForActivityResult with RequestPermission)
 *     4. React to the user's Allow/Deny response
 */
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContactsAdapter

    // Modern way to request a single permission — replaces the old
    // requestPermissions() + onRequestPermissionsResult() pattern
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // User pressed Allow — load contacts now
                loadContacts()
            } else {
                // User pressed Deny
                Toast.makeText(this, "Permission denied — cannot read contacts.", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up RecyclerView with an empty list; we'll fill it after permission is granted
        adapter = ContactsAdapter(emptyList())
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        checkAndLoadContacts()
    }

    private fun checkAndLoadContacts() {
        when {
            // Permission was already granted (e.g. user allowed it before)
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                    == PackageManager.PERMISSION_GRANTED -> {
                loadContacts()
            }

            // Android recommends showing a rationale if the user previously denied
            shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                Toast.makeText(
                    this,
                    "Contacts permission is needed to display your contacts list.",
                    Toast.LENGTH_LONG
                ).show()
                requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
            }

            // First time asking — just show the system dialog
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
            }
        }
    }

    /**
     * Query the contacts ContentProvider and return a list of (name, phone) pairs.
     *
     * ContentResolver.query() parameters:
     *   uri         — which table to query (like a table name in SQL)
     *   projection  — which columns to return (like SELECT columns)
     *   selection   — WHERE clause (null = no filter)
     *   selectionArgs — values for ? placeholders in selection
     *   sortOrder   — ORDER BY clause
     */
    private fun loadContacts() {
        val contacts = mutableListOf<Contact>()

        // The URI for the phone number table
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

        // We only need name and phone — fetching all columns is wasteful
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        // contentResolver is provided by the Activity; it routes the query to the
        // correct ContentProvider (the Contacts app in this case)
        val cursor = contentResolver.query(
            uri,
            projection,
            null,   // no WHERE filter
            null,
            "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} ASC"  // sort A→Z
        )

        // Cursor works like an iterator — move to each row and read columns by index
        cursor?.use { c ->
            val nameIndex   = c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val numberIndex = c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)

            while (c.moveToNext()) {
                contacts.add(
                    Contact(
                        name   = c.getString(nameIndex),
                        phone  = c.getString(numberIndex)
                    )
                )
            }
        }

        // Hand the list to the adapter — it will tell RecyclerView to redraw
        adapter.updateContacts(contacts)

        if (contacts.isEmpty()) {
            Toast.makeText(this, "No contacts found on this device.", Toast.LENGTH_SHORT).show()
        }
    }
}
