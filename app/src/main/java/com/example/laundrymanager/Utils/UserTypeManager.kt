package com.example.laundrymanager.Utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserTypeManager @Inject constructor(@ApplicationContext context: Context) {
    private var prefs = context.getSharedPreferences(Utilities.PREFS_TOKEN_FILE, Context.MODE_PRIVATE)

    fun saveUserType(token: String) {
        val editor = prefs.edit()
        editor.putString(Utilities.USER_TYPE, token)
        editor.apply()
    }

    fun removeUserType() {
        val editor = prefs.edit()
        editor.putString(Utilities.USER_TYPE, null)
        editor.apply()
    }

    fun getUserType() : String? {
        return prefs.getString(Utilities.USER_TYPE, null)
    }
}