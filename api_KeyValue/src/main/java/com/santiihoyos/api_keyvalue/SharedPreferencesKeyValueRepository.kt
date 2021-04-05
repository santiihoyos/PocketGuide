package com.santiihoyos.api_keyvalue

import android.content.SharedPreferences
import com.santiihoyos.base_api.repository.KeyValueRepository
import javax.inject.Inject

/**
 * Implementation of KeyValueRepository
 */
internal class SharedPreferencesKeyValueRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : KeyValueRepository {

    override fun saveStringPreference(key: String, value: String) = sharedPreferences.edit()
        .putString(key, value).commit()

    override fun readStringPreference(key: String, default: String) = sharedPreferences
        .getString(key, default)!!
}
