package com.cockerspaniel.luncnow.util.storage

import android.content.SharedPreferences
import com.google.gson.Gson

class PreferenceStoreImpl(
    private val encryptedPreferences: SharedPreferences,
    private val gson: Gson
) : PreferenceStore {

    override fun retrieveAll(): MutableMap<String, *> = encryptedPreferences.all

    override fun persistBoolean(key: String, value: Boolean) {
        encryptedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun retrieveBoolean(key: String): Boolean? {
        return if (encryptedPreferences.contains(key)) {
            encryptedPreferences.getBoolean(key, false)
        } else null
    }

    override fun persistString(key: String, value: String) {
        encryptedPreferences.edit().putString(key, value).apply()
    }

    override fun retrieveString(key: String): String? {
        return encryptedPreferences.getString(key, null)
    }

    override fun persistObject(key: String, value: Any) {
        persistString(key, gson.toJson(value))
    }

    override fun <T : Any> retrieveObject(key: String, type: Class<T>): T? {
        return gson.fromJson(retrieveString(key), type)
    }

    override fun removeEntry(key: String) {
        encryptedPreferences.edit().remove(key).apply()
    }

    override fun removeAll() {
        encryptedPreferences.edit().clear().apply()
    }

    override fun copyToEncryptedPreferences(entries: List<Pair<String, Any?>>) {
        for ((key, value) in entries) {
            set(key, value)
        }
    }

    private fun <T : Any> set(key: String, value: T?) {
        when (value) {
            is Boolean -> persistBoolean(key, value)
            is String -> persistString(key, value)
            else -> {
                value?.let { persistObject(key, value) }
            }
        }
    }
}
