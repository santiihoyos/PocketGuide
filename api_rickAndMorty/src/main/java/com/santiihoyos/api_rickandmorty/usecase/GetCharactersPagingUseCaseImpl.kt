package com.santiihoyos.api_rickandmorty.usecase

import com.santiihoyos.api_rickandmorty.RickAndMortyRestRepository
import com.santiihoyos.api_rickandmorty.response.CharactersResponse
import retrofit2.HttpException
import com.santiihoyos.base_api.usecase.UseCaseException
import java.lang.Exception
import javax.inject.Inject

internal class GetCharactersPagingUseCaseImpl @Inject constructor(
    private val rickAndMortyRestRepository: RickAndMortyRestRepository
) : GetCharactersPagingUseCase() {

    override suspend fun execute(pageNumber: Int): CharactersResponse = try {

        rickAndMortyRestRepository.getCharactersAtPage(pageNumber)

    } catch (ex: HttpException) {

        throw UseCaseException(
            httpCode = ex.code(),
            message = ex.message()
        )
    } catch (ex: Exception) {

        TODO("manage other possible errors of this endpoint")
    }
}
