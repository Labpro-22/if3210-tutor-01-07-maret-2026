package com.example.tutor

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Branch 03 — Resources
 *
 * Android organises non-code assets in the res/ folder.
 * The build system generates a class called R (in your package) that gives you
 * compile-time references to every resource:
 *
 *   R.drawable.ic_sample      → res/drawable/ic_sample.xml
 *   R.string.app_name         → res/values/strings.xml  <string name="app_name">
 *   R.dimen.screen_padding    → res/values/dimens.xml   <dimen name="screen_padding">
 *   R.id.ivStar               → the view with android:id="@+id/ivStar" in a layout
 *   R.layout.activity_main    → res/layout/activity_main.xml
 *   R.color.purple_500        → res/values/colors.xml   <color name="purple_500">
 *
 * Using R keeps everything type-safe and refactor-friendly — the compiler will
 * catch a missing resource at build time rather than crashing at runtime.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // R.layout reference

        // ── Drawable resource ──────────────────────────────────────────────
        val ivStar: ImageView = findViewById(R.id.ivStar)  // R.id reference
        // R.drawable.ic_sample resolves to res/drawable/ic_sample.xml
        ivStar.setImageResource(R.drawable.ic_sample)

        // ── String resource ────────────────────────────────────────────────
        val tvStringDemo: TextView = findViewById(R.id.tvStringDemo)
        // getString() fetches the value from res/values/strings.xml at runtime
        tvStringDemo.text = getString(R.string.msg_string_resource)

        // ── Dimen resource ─────────────────────────────────────────────────
        val tvDimenDemo: TextView = findViewById(R.id.tvDimenDemo)
        // resources.getDimension() returns the dimen value in pixels (already scaled)
        // We display it here just to show the concept
        val paddingPx = resources.getDimension(R.dimen.screen_padding)
        tvDimenDemo.text = "screen_padding = ${paddingPx.toInt()} px on this device\n" +
                "(defined as 16dp in dimens.xml)"
    }
}
