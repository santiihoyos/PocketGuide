package com.santiihoyos.api_marvel.usecase.implementation

import com.santiihoyos.api_marvel.MarvelRestRepository
import com.santiihoyos.api_marvel.response.CharacterResponse
import com.santiihoyos.api_marvel.usecase.GetCharacterByIdUseCase
import com.santiihoyos.base_api.usecase.UseCaseException
import retrofit2.HttpException
import java.lang.Exception
import java.net.HttpURLConnection
import javax.inject.Inject
import kotlin.jvm.Throws

internal class GetCharacterByIdUseCaseImpl @Inject constructor(
    private val marvelRestRepository: MarvelRestRepository
) : GetCharacterByIdUseCase() {

    @Throws(UseCaseException::class)
    override suspend fun execute(characterId: String): CharacterResponse? {

        return try {

            marvelRestRepository.getCharactersById(characterId)
        } catch (ex: HttpException) {

            //character not found
            if (ex.code() == HttpURLConnection.HTTP_NOT_FOUND) {

                null
            } else {

                //TODO: manage other httpExceptions here
                throw UseCaseException(ex.code(), ex.message())
            }
        } catch (ex: Exception) {

            //TODO: manage other rare exceptions here...
            throw UseCaseException(
                httpCode = -1,
                message = "Error on GetCharacterByIdUseCaseImpl"
            )
        }
    }
}
