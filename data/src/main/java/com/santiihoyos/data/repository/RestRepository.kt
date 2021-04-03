package com.santiihoyos.data.repository

import com.santiihoyos.data.response.CharacterResponse
import com.santiihoyos.data.response.CharactersResponse
import retrofit2.Call

interface RestRepository {

    /**
     * Get all characters of Rick&Morty universe
     * Paginated!!!
     */
    suspend fun getCharactersAtPage(page: Int): CharactersResponse

    /**
     * Get Only one character searching by id
     */
    suspend fun getCharacterById(id: String): CharacterResponse
}
