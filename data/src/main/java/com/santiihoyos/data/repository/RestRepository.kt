package com.santiihoyos.data.repository

import com.santiihoyos.data.response.CharacterResponse
import com.santiihoyos.data.response.CharactersResponse

interface RestRepository {

    /**
     * Get all characters of Rick&Morty universe
     * Paginated!!!
     */
    fun getCharactersPage(page: Int): CharactersResponse

    /**
     * Get Only one character searching by id
     */
    fun getCharacterById(id: String): CharacterResponse
}
