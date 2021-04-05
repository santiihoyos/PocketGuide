package com.santiihoyos.api_rickandmorty.usecase

import com.santiihoyos.api_rickandmorty.RickAndMortyRestRepository
import com.santiihoyos.api_rickandmorty.response.CharactersResponse
import retrofit2.HttpException
import javax.inject.Inject

class GetCharactersPagingUseCase @Inject constructor(
    private val rickAndMortyRestRepository: RickAndMortyRestRepository
) {

    data class Response(
        val httpStatus: Int? = null,
        val response: CharactersResponse? = null,
        val errorInfo: String? = null
    )

    suspend fun execute(pageNumber: Int): Response = try {

        val response = rickAndMortyRestRepository.getCharactersAtPage(pageNumber)
        Response(200, response)

    } catch (ex: HttpException) {

        Response(ex.code(), null, ex.message())
    }
}
