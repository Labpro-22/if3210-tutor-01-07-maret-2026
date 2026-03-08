package com.example.tutor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * Branch 02 — Layouts
 *
 * This screen is a "menu" that lets you open three separate
 * activities, each demonstrating a different layout type.
 *
 * Android has many layout containers; the three most important ones for
 * beginners are:
 *   • LinearLayout   — arrange views in a single row or column
 *   • FrameLayout    — stack views on top of each other (like layers)
 *   • ConstraintLayout — position views using rules ("constraints")
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Each button starts a different Activity to show one layout type
        findViewById<Button>(R.id.btnLinear).setOnClickListener {
            startActivity(Intent(this, LinearDemoActivity::class.java))
        }

        findViewById<Button>(R.id.btnFrame).setOnClickListener {
            startActivity(Intent(this, FrameDemoActivity::class.java))
        }

        findViewById<Button>(R.id.btnConstraint).setOnClickListener {
            startActivity(Intent(this, ConstraintDemoActivity::class.java))
        }
    }
}
