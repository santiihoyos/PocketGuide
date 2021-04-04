package com.santiihoyos.repositoryrickandmorty

import com.santiihoyos.repositoryrickandmorty.response.CharacterResponse
import com.santiihoyos.repositoryrickandmorty.response.CharactersResponse

internal class RickAndMortyMockRestRepository: RickAndMortyRestRepository {

    override suspend fun getCharactersAtPage(page: Int): CharactersResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterById(id: String): CharacterResponse? {
        TODO("Not yet implemented")
    }
}
