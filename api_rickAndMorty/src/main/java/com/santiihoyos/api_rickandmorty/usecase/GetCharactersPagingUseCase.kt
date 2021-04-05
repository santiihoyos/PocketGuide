package com.santiihoyos.api_rickandmorty.usecase

import com.santiihoyos.api_rickandmorty.response.CharactersResponse
import retrofit2.HttpException
import java.lang.Exception

abstract class GetCharactersPagingUseCase {

    abstract suspend fun execute(pageNumber: Int): CharactersResponse
}
