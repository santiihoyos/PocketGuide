package com.santiihoyos.api_rickandmorty

import com.santiihoyos.base_api.repository.RestRepository
import com.santiihoyos.api_rickandmorty.response.CharacterResponse
import com.santiihoyos.api_rickandmorty.response.CharactersResponse

internal interface RickAndMortyRestRepository : RestRepository {

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
