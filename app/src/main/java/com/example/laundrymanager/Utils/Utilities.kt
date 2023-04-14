package com.example.laundrymanager.Utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object Utilities {
    const val BASE_URL = "http://10.5.170.241:9000/"
    const val PREFERENCE_NAME = "UserPreferences"
    const val PREFS_TOKEN_FILE = "PREFS_TOKEN_FILE"
    const val USER_TYPE = "USER_TYPE"
    const val USER_TOKEN = "USER_TOKEN"

    fun showSnackBar(message: String, view: View) {
        val snackBar: Snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackBar.show()
    }
}

