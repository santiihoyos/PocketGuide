package com.santiihoyos.api_marvel

import com.santiihoyos.api_marvel.response.HeroResponse
import com.santiihoyos.api_marvel.response.HeroesResponse
import com.santiihoyos.base_api.repository.RestRepository


interface MarvelRestRepository : RestRepository {

    /**
     * Get all characters of Rick&Morty universe
     * Paginated!!!
     */
    suspend fun getCharactersAtPage(page: Int): HeroesResponse

    /**
     * Get Only one character searching by id
     */
    suspend fun getCharacterById(id: String): HeroResponse?

}
