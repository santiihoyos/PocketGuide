package com.santiihoyos.data.repository.implementation

import com.santiihoyos.data.repository.RestRepository
import com.santiihoyos.data.response.CharacterResponse
import com.santiihoyos.data.response.CharactersResponse

class MockRestRepository : RestRepository {

    override fun getCharactersPage(page: Int): CharactersResponse {
        TODO("Not yet implemented")
    }

    override fun getCharacterById(id: String): CharacterResponse {
        TODO("Not yet implemented")
    }
}
