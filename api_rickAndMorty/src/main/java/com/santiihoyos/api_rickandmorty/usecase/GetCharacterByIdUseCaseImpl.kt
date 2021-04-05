package com.santiihoyos.api_rickandmorty.usecase

import com.santiihoyos.api_rickandmorty.RickAndMortyRestRepository
import com.santiihoyos.api_rickandmorty.response.CharacterResponse
import com.santiihoyos.base_api.usecase.UseCaseException
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject
import kotlin.jvm.Throws

internal class GetCharacterByIdUseCaseImpl @Inject constructor(
    private val rickAndMortyRestRepository: RickAndMortyRestRepository
) : GetCharacterByIdUseCase() {

    @Throws(UseCaseException::class)
    override suspend fun execute(characterId: String): CharacterResponse = try {

        rickAndMortyRestRepository.getCharacterById(characterId)
    } catch (ex: HttpException) {

        throw UseCaseException(
            httpCode = ex.code(),
            message = ex.message()
        )
    } catch (ex: Exception) {

        TODO("manage other possible errors of this endpoint")
    }

}
