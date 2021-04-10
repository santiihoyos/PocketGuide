package com.santiihoyos.api_marvel

import com.santiihoyos.api_marvel.response.CharacterResponse
import com.santiihoyos.base_api.repository.RestRepository


internal interface MarvelRestRepository : RestRepository {

    /**
     * Get all heroes of Disney & Marvel universe
     * This list is paginated.
     *
     * @param page - current page to read
     * @return HeroesResponse with server response
     */
    suspend fun getCharactersByPage(
        orderBy: String,
        offset: Int,
        limit: Int
    ): CharacterResponse

    /**
     * Get Only one hero searching by id
     *
     * @param id - unique id of hero
     * @return HeroesResponse with server response
     */
    suspend fun getCharactersById(id: String): CharacterResponse?

}
