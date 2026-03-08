package com.example.tutor

import android.content.Intent
import android.os.Bundle
import android.view.accessibility.AccessibilityEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat

/**
 * Branch 04 — Accessibility
 *
 * Android's accessibility system lets users with visual or motor impairments
 * use your app via TalkBack (screen reader) or Switch Access.
 *
 * TalkBack reads aloud:
 *   • android:contentDescription  → for ImageView, ImageButton, etc.
 *   • android:hint / android:text → for EditText and TextView
 *   • android:labelFor            → links a label TextView to an EditText so
 *                                   TalkBack reads "Your name — edit box"
 *
 * When content changes dynamically (e.g. a status message updates), you must
 * tell TalkBack to re-read it using:
 *   view.announceForAccessibility("message")
 *   — OR —
 *   android:accessibilityLiveRegion="polite" in XML (Android handles it automatically)
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivLogo: ImageView   = findViewById(R.id.ivLogo)
        val etName: EditText    = findViewById(R.id.etName)
        val btnSubmit: Button   = findViewById(R.id.btnSubmit)
        val tvStatus: TextView  = findViewById(R.id.tvStatus)
        val btnAnnounce: Button = findViewById(R.id.btnAnnounce)

        // contentDescription can also be set in code (same effect as XML attribute)
        // Useful when the description depends on dynamic data
        ivLogo.contentDescription = getString(R.string.cd_logo)

        // Submit: update status TextView — TalkBack reads it because of liveRegion
        btnSubmit.setOnClickListener {
            val name = etName.text.toString().trim()
            tvStatus.text = if (name.isNotEmpty()) "Hello, $name! 👋" else "Please enter your name."
            // No extra code needed — accessibilityLiveRegion="polite" in XML handles it
        }

        // announceForAccessibility: explicitly pushes a message to TalkBack
        // Use this when you want to announce something that isn't in a live region
        btnAnnounce.setOnClickListener {
            val name = etName.text.toString().trim()
            val message = if (name.isNotEmpty()) "Greeting sent to $name" else "No name entered"
            // This queues the string to be spoken by the screen reader immediately
            tvStatus.announceForAccessibility(message)
            tvStatus.text = message
        }
    }
}
