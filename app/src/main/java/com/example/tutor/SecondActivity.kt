package com.example.tutor

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Branch 05 — SecondActivity
 *
 * Receives data passed from MainActivity via Intent extras.
 *
 * The back stack:
 *   MainActivity → (startActivity) → SecondActivity
 *   Pressing Back pops SecondActivity and returns to MainActivity.
 *
 * Retrieve extras with intent.getStringExtra(key), intent.getIntExtra(key, default), etc.
 */
class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // 'intent' is a property of Activity — it's the Intent that started this Activity
        val name = intent.getStringExtra(MainActivity.EXTRA_NAME)

        val tvWelcome: TextView = findViewById(R.id.tvWelcome)
        // Use the received value, fall back to "stranger" if nothing was passed
        tvWelcome.text = if (!name.isNullOrBlank()) {
            "Welcome, $name!\n\nYou arrived here via an explicit Intent from MainActivity."
        } else {
            "No name was passed.\n\nTry typing your name before pressing the button."
        }
    }
}
