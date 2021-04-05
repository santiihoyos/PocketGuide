package com.santiihoyos.base_api.repository

/**
 * This class provides contract and common logic to all
 * repositories implementations to manage Preferences pairs <String,Object>
 */
interface KeyValueRepository {

    /**
     * Saves String into device key-value storage
     *
     * @param key key for value
     * @param value value for key
     *
     * @return operation result (true = successful)
     */
    fun saveStringPreference(key: String, value: String): Boolean

    /**
     * Reads one string value by key
     *
     * @param key for value
     * @param default String to use if value is null
     *
     * @return key's value or default if key does not exist.
     */
    fun readStringPreference(key: String, default: String): String


    //NOTE: add other supported types here...
}
