package com.example.tutor

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

/**
 * Branch 05 — Activities and Intents
 *
 * An Intent is a message object that describes an operation to perform.
 * Two kinds:
 *
 * ① Explicit Intent — you name the exact class to start.
 *   Used to navigate between screens in YOUR OWN app.
 *   Example: start SecondActivity and hand it the user's name.
 *
 * ② Implicit Intent — you describe the ACTION and data, and Android
 *   picks the right app to handle it (email client, browser, maps, …).
 *   Your app doesn't need to know which app handles it.
 *
 * Step 5a: Explicit intent → SecondActivity
 * Step 5b: Implicit intent → email client
 */
class MainActivity : AppCompatActivity() {

    // Key used to attach/retrieve the extra — define it as a constant to avoid typos
    companion object {
        const val EXTRA_NAME = "com.example.tutor.EXTRA_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName: EditText = findViewById(R.id.etName)

        // ── 5a: Explicit Intent ────────────────────────────────────────────
        findViewById<Button>(R.id.btnOpenSecond).setOnClickListener {
            // Create an intent that explicitly targets SecondActivity
            val intent = Intent(this, SecondActivity::class.java)

            // Attach data using putExtra(key, value)
            // The key is just a string; using a package-qualified name avoids collisions
            intent.putExtra(EXTRA_NAME, etName.text.toString().trim())

            // Start the activity — Android pushes SecondActivity onto the back stack
            startActivity(intent)
        }

        // ── 5b: Implicit Intent — email ────────────────────────────────────
        findViewById<Button>(R.id.btnEmail).setOnClickListener {
            // ACTION_SENDTO + "mailto:" URI targets email clients specifically.
            // (ACTION_SEND would also match Bluetooth, messaging apps, etc.)
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")                   // only email apps handle this
                putExtra(Intent.EXTRA_EMAIL, arrayOf("lecturer@university.ac.id"))
                putExtra(Intent.EXTRA_SUBJECT, "Android Tutorial Question")
                putExtra(Intent.EXTRA_TEXT, "Hi,\n\nI have a question about the Android tutorial.\n\nRegards,\n${etName.text}")
            }

            // resolveActivity checks whether ANY app can handle this intent
            // before we call startActivity() — avoids a crash if no email app is installed
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }
}
