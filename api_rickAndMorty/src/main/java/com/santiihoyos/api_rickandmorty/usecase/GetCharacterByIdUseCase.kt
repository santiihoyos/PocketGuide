package com.santiihoyos.api_rickandmorty.usecase

import com.santiihoyos.api_rickandmorty.RickAndMortyRestRepository
import com.santiihoyos.api_rickandmorty.response.CharacterResponse
import com.santiihoyos.base_api.usecase.UseCaseException
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val rickAndMortyRestRepository: RickAndMortyRestRepository
) {

    suspend fun execute(characterId: String): CharacterResponse = try {

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
