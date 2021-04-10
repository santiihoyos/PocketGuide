package com.santiihoyos.api_rickandmorty.usecase.implementation

import com.santiihoyos.api_rickandmorty.RickAndMortyRestRepository
import com.santiihoyos.api_rickandmorty.response.CharactersResponse
import com.santiihoyos.api_rickandmorty.usecase.GetCharactersPagingUseCase
import retrofit2.HttpException
import com.santiihoyos.base_api.usecase.UseCaseException
import java.lang.Exception
import javax.inject.Inject
import kotlin.jvm.Throws

internal class GetCharactersPagingUseCaseImpl @Inject constructor(
    private val rickAndMortyRestRepository: RickAndMortyRestRepository
) : GetCharactersPagingUseCase() {

    @Throws(UseCaseException::class)
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
