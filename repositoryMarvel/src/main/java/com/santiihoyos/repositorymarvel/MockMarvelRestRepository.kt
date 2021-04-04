package com.santiihoyos.repositorymarvel

import com.santiihoyos.repositorymarvel.response.HeroResponse
import com.santiihoyos.repositorymarvel.response.HeroesResponse

internal class MockMarvelRestRepository : MarvelRestRepository {

    override suspend fun getCharactersAtPage(page: Int): HeroesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterById(id: String): HeroResponse? {
        TODO("Not yet implemented")
    }
}
