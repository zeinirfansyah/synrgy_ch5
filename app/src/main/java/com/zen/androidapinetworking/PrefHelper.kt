package com.zen.androidapinetworking

import android.content.Context
import android.content.SharedPreferences


class PrefHelper(context: Context) {
    private val PREF_NAME = "ZenNotAppSharedPreferences"
    private val preferences : SharedPreferences
    val editor: SharedPreferences.Editor

    init {
        preferences = context.getSharedPreferences(PREF_NAME, 0)
        editor = preferences.edit()
    }

    fun put(key:String, value: String){
        editor.putString(key, value)
            .apply()
    }

    fun getString(key: String): String? {
        return preferences.getString(key, null)
    }

    fun put(key: String, value: Boolean){
        editor.putBoolean(key,value)
            .apply()
    }
    fun getBoolean(key: String, value: Boolean): Boolean {
        return preferences.getBoolean(key, false)
    }

    fun clear(){
        editor.clear()
            .apply()
    }

}