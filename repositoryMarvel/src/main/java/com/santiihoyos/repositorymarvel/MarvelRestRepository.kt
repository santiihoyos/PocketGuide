package com.santiihoyos.repositorymarvel

import com.santiihoyos.base_repository.repository.RestRepository
import com.santiihoyos.repositorymarvel.response.HeroResponse
import com.santiihoyos.repositorymarvel.response.HeroesResponse


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
