package kz.weshop.unioncompanyservice.common.preferences

import android.content.SharedPreferences

class UserSession(private val prefs: SharedPreferences) {

    companion object {
        const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
    }

    fun getAccessToken(): String? = prefs.getString(KEY_ACCESS_TOKEN, null)
    fun setAccessToken(access_token: String) {
        prefs.edit().putString(KEY_ACCESS_TOKEN, access_token).apply()
    }

    fun clear() {
        prefs.edit().clear().apply()
    }

}