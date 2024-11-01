package com.marsanix.sharedpreferencesapps2

import android.content.Context

const val NAME_PREF = "PREF_PRIVATE"
const val USERNAME_KEY = "_USERNAME"
const val ISLOGIN_KEY = "_ISLOGIN"

class SharedPreferencesManager(context: Context) {
    private val preferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    var username
        get() = preferences.getString(USERNAME_KEY, "")
        set(value) {
            editor.putString(USERNAME_KEY, value)
            editor.commit()
        }

    var isLogin
        get() = preferences.getBoolean(ISLOGIN_KEY, false)
        set(value) {
            editor.putBoolean(ISLOGIN_KEY, value)
            editor.commit()
        }

    fun clear() {
        editor.clear()
        editor.commit()
    }

}