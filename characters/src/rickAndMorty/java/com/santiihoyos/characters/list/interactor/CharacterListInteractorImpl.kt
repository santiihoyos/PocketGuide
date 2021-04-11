package com.santiihoyos.characters.list.interactor

import com.santiihoyos.api_rickandmorty.usecase.GetCharactersPagingUseCase
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.base_api.usecase.UseCaseException
import com.santiihoyos.characters.entity.mappers.CharacterMapper
import javax.inject.Inject

class CharacterListInteractorImpl @Inject constructor(
    private val getCharactersPagingUseCaseImpl: GetCharactersPagingUseCase,
    private val characterMapper: CharacterMapper
) : CharacterListInteractor() {

    override suspend fun getNextCharacters(page: Int): List<Character>? = try {

        getCharactersPagingUseCaseImpl.execute(page).results.map(characterMapper::map)
    } catch (ex: UseCaseException) {

        null
    }
}
