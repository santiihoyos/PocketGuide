package com.santiihoyos.data.repository.implementation

import com.santiihoyos.data.repository.RestRepository
import com.santiihoyos.data.response.CharacterResponse
import com.santiihoyos.data.response.CharactersResponse
import retrofit2.Call

internal class MockRestRepository : RestRepository {

    override suspend fun getCharactersAtPage(page: Int): CharactersResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterById(id: String): CharacterResponse {
        TODO("Not yet implemented")
    }
}
