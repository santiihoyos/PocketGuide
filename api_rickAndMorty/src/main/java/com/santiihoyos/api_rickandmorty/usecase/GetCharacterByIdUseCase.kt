package com.santiihoyos.api_rickandmorty.usecase

import com.santiihoyos.api_rickandmorty.response.CharacterResponse
import com.santiihoyos.base_api.usecase.UseCaseException
import kotlin.jvm.Throws

abstract class GetCharacterByIdUseCase {

    @Throws(UseCaseException::class)
    abstract suspend fun execute(characterId: String): CharacterResponse

}
