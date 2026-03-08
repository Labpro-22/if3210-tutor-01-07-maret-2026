package com.example.tutor

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Branch 01 — Basic Views
 *
 * Demonstrates the four most common Android widgets:
 *   • TextView  — displays read-only text
 *   • EditText  — lets the user type text
 *   • Button    — triggers an action on click
 *   • ImageView — displays a drawable/image
 *
 * Views are wired to Kotlin variables via findViewById(), which looks up
 * a view by the id you gave it in XML (e.g. android:id="@+id/tvGreeting").
 */
class MainActivity : AppCompatActivity() {

    // Keep click count across configuration changes would need ViewModel,
    // but for simplicity we store it as a field here.
    private var clickCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Tell Android which XML layout file to inflate for this screen
        setContentView(R.layout.activity_main)

        // --- Wire up views ---
        // R.id.tvGreeting refers to the view with android:id="@+id/tvGreeting" in XML
        val tvGreeting: TextView  = findViewById(R.id.tvGreeting)
        val etName: EditText      = findViewById(R.id.etName)
        val btnGreet: Button      = findViewById(R.id.btnGreet)
        val tvCounter: TextView   = findViewById(R.id.tvCounter)
        val btnCounter: Button    = findViewById(R.id.btnCounter)
        val ivLogo: ImageView     = findViewById(R.id.ivLogo)

        // Set an image resource programmatically (R.drawable references res/drawable/)
        ivLogo.setImageResource(R.drawable.ic_launcher_foreground)

        // --- Greeting button ---
        // setOnClickListener accepts a lambda that runs when the button is tapped
        btnGreet.setOnClickListener {
            val name = etName.text.toString().trim()
            // Update the TextView's text property at runtime
            tvGreeting.text = if (name.isNotEmpty()) "Hello, $name!" else "Hello, stranger!"
        }

        // --- Counter button ---
        btnCounter.setOnClickListener {
            clickCount++                              // increment our in-memory counter
            tvCounter.text = "Clicked: $clickCount"  // update the label
        }
    }
}
