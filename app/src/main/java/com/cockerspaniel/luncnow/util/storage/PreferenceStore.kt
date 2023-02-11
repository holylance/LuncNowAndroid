package com.cockerspaniel.luncnow.util.storage

interface PreferenceStore {
    fun removeAll()
    fun removeEntry(key: String)
    fun retrieveAll(): MutableMap<String, *>?
    fun retrieveBoolean(key: String): Boolean?
    fun <T : Any> retrieveObject(key: String, type: Class<T>): T?
    fun persistBoolean(key: String, value: Boolean)
    fun persistObject(key: String, value: Any)
    fun persistString(key: String, value: String)
    fun retrieveString(key: String): String?

    /**
     * This is a function to be used for the migration purpose,
     * after that it can be removed
     */
    fun copyToEncryptedPreferences(entries: List<Pair<String, Any?>>)
}
