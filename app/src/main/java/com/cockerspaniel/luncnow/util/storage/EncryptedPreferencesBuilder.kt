package com.cockerspaniel.luncnow.util.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class EncryptedPreferencesBuilder(val context: Context, val scope: String = "APP") {

    fun instantiateEncryptedSharedPreferences(): SharedPreferences {
        val sharedPreferencesName = "BankingAppEncryptedSharedPreferences-$scope"
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            sharedPreferencesName,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}
