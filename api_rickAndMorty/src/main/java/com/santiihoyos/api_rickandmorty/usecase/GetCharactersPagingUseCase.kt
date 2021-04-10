package com.santiihoyos.api_rickandmorty.usecase

import com.santiihoyos.api_rickandmorty.response.CharactersResponse
import com.santiihoyos.base_api.usecase.UseCaseException
import retrofit2.HttpException
import java.lang.Exception
import kotlin.jvm.Throws

abstract class GetCharactersPagingUseCase {

    @Throws(UseCaseException::class)
    abstract suspend fun execute(pageNumber: Int): CharactersResponse
}
