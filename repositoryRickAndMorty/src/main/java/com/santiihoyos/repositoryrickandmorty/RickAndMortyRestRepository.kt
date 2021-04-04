package com.santiihoyos.repositoryrickandmorty

import com.santiihoyos.base_repository.repository.RestRepository
import com.santiihoyos.repositoryrickandmorty.response.CharacterResponse
import com.santiihoyos.repositoryrickandmorty.response.CharactersResponse


interface RickAndMortyRestRepository: RestRepository {

    /**
     * Get all characters of Rick&Morty universe
     * Paginated!!!
     */
    suspend fun getCharactersAtPage(page: Int): CharactersResponse

    /**
     * Get Only one character searching by id
     */
    suspend fun getCharacterById(id: String): CharacterResponse?

}
