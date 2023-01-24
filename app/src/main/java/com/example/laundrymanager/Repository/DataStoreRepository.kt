package com.example.laundrymanager.Repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.laundrymanager.Utils.Utilities.PREFERENCE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


class DataStoreRepository @Inject constructor(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

    private object PreferencesKeys {
        val token = stringPreferencesKey("token")
    }

    suspend fun setNewUserToken(newUserToken: String) {
        context.dataStore.edit {
            it[PreferencesKeys.token] = newUserToken
        }
    }

    val getCurrentUserToken: Flow<String> = context.dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        preferences[PreferencesKeys.token] ?: "none"
    }
}