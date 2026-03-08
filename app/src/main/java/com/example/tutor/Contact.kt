package com.example.tutor

/**
 * Simple data class representing one contact entry.
 * Kotlin data classes auto-generate equals(), hashCode(), toString(), and copy().
 */
data class Contact(
    val name: String,
    val phone: String
)
