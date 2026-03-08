package com.example.tutor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/** Just inflates the layout — all the demo content is in activity_constraint.xml */
class ConstraintDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint)
    }
}
