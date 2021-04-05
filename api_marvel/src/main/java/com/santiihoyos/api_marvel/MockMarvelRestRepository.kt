package com.santiihoyos.api_marvel

import com.santiihoyos.api_marvel.response.HeroResponse
import com.santiihoyos.api_marvel.response.HeroesResponse

internal class MockMarvelRestRepository : MarvelRestRepository {

    override suspend fun getCharactersAtPage(page: Int): HeroesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterById(id: String): HeroResponse? {
        TODO("Not yet implemented")
    }
}
