package com.shubham.brainyexplorers.data.model

/**
 * Data class representing a Lab item from Firestore.
 * @property title The title of the lab.
 * @property url The URL to open for this lab.
 */
data class LabItem(
    val title: String = "",
    val url: String = ""
) 