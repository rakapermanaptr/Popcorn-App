package com.rakapermanaputra.popcorn.db

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context) {
    private val PREF_NAME = "accountTMDBUser"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_NAME, value)
        editor!!.commit()
    }

    fun saveInt(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_NAME, value)
        editor!!.commit()
    }

    fun getValueString(KEY_NAME: String): String? {
        return sharedPref.getString(KEY_NAME, null)
    }

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.commit()
    }
}