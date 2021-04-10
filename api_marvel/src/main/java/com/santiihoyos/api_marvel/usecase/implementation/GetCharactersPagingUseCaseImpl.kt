package com.santiihoyos.api_marvel.usecase.implementation

import com.santiihoyos.api_marvel.MarvelRestRepository
import com.santiihoyos.api_marvel.response.CharacterResponse
import com.santiihoyos.api_marvel.usecase.CHARACTERS_ORDER_BY
import com.santiihoyos.api_marvel.usecase.CHARACTERS_SIZE_PAGE
import com.santiihoyos.api_marvel.usecase.GetCharactersPagingUseCase
import com.santiihoyos.base_api.usecase.UseCaseException
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.jvm.Throws

internal class GetCharactersPagingUseCaseImpl @Inject constructor(
    private val marvelRestRepository: MarvelRestRepository
): GetCharactersPagingUseCase() {

    @Throws(UseCaseException::class)
    override suspend fun execute(page: Int): CharacterResponse {

        return try {

            marvelRestRepository.getCharactersByPage(
                orderBy = CHARACTERS_ORDER_BY,
                offset = page * CHARACTERS_SIZE_PAGE,
                limit = CHARACTERS_SIZE_PAGE
            )
        } catch (ex: HttpException) {

            throw UseCaseException(
                httpCode = ex.code(),
                message = ex.message()
            )
        } catch (ex: Exception) {

            //TODO: manage other exceptions here...
            throw UseCaseException(
                httpCode = -1,
                message = "Error on GetCharactersPagingUseCase: ${ex.cause}"
            )
        }
    }
}
