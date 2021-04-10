package com.santiihoyos.api_marvel

import com.santiihoyos.api_marvel.response.CharacterResponse

internal class MarvelRestRepositoryMockImpl : MarvelRestRepository {

    override suspend fun getCharactersByPage(orderBy: String, offset: Int, limit: Int): CharacterResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getCharactersById(id: String): CharacterResponse {
        TODO("Not yet implemented")
    }
}
