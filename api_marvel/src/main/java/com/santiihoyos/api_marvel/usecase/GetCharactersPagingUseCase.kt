package com.santiihoyos.api_marvel.usecase

import com.santiihoyos.api_marvel.response.CharacterResponse
import com.santiihoyos.base_api.usecase.UseCaseException
import kotlin.jvm.Throws

const val CHARACTERS_SIZE_PAGE = 20
const val CHARACTERS_ORDER_BY = "name"

abstract class GetCharactersPagingUseCase {

    /**
     * get paginated list of characters
     *
     */
    @Throws(UseCaseException::class)
    abstract suspend fun execute(page: Int): CharacterResponse
}
