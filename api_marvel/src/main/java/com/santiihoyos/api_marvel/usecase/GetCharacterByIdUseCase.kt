package com.santiihoyos.api_marvel.usecase

import com.santiihoyos.api_marvel.response.CharacterResponse
import com.santiihoyos.base_api.usecase.UseCaseException
import kotlin.jvm.Throws

abstract class GetCharacterByIdUseCase {

    /**
     * Search one character by id into api
     *
     * @param characterId - id of required character
     * @return CharacterResponse or null if not exists
     */
    @Throws(UseCaseException::class)
    abstract suspend fun execute(characterId: String): CharacterResponse?

}
