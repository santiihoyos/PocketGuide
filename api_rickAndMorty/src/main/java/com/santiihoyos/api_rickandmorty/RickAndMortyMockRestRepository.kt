package com.santiihoyos.api_rickandmorty

import com.santiihoyos.api_rickandmorty.response.CharacterResponse
import com.santiihoyos.api_rickandmorty.response.CharactersResponse

internal class RickAndMortyMockRestRepository: RickAndMortyRestRepository {

    override suspend fun getCharactersAtPage(page: Int): CharactersResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterById(id: String): CharacterResponse {
        TODO("Not yet implemented")
    }
}
