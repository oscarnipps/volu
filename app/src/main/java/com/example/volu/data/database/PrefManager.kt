package com.example.volu.data.database

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.volu.data.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PrefManager @Inject constructor(@ApplicationContext val context: Context) {

    private var sharedPreference =
        context.getSharedPreferences(Constants.SHARED_PREFERENCE_KEY, MODE_PRIVATE)


    fun saveItem(key: String, value: String) {
        val editor = sharedPreference.edit()

        editor.putString(key, value)

        editor.apply()
    }

    fun saveItem(key: String, value: Boolean) {
        val editor = sharedPreference.edit()

        editor.putBoolean(key, value)

        editor.apply()
    }

    fun saveItem(key: String, value: Int) {
        val editor = sharedPreference.edit()

        editor.putInt(key, value)

        editor.apply()
    }

    fun saveItem(key: String, value: Long) {
        val editor = sharedPreference.edit()

        editor.putLong(key, value)

        editor.apply()
    }

    fun getIntItem(key: String): Int {
      return sharedPreference.getInt(key,0)
    }

    fun getStringItem(key: String): String? {
        return sharedPreference.getString(key,"")
    }

    fun getLongItem(key: String): Long {
        return sharedPreference.getLong(key,0)
    }

    fun getBooleanItem(key: String): Boolean {
        return sharedPreference.getBoolean(key,false)
    }
}