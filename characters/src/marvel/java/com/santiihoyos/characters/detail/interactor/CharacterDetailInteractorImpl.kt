@file:Suppress("LiftReturnOrAssignment")

package com.santiihoyos.characters.detail.interactor

import android.util.Log
import com.santiihoyos.api_marvel.usecase.GetCharacterByIdUseCase
import com.santiihoyos.base_api.repository.KeyValueRepository
import com.santiihoyos.base_api.usecase.UseCaseException
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.characters.entity.mappers.CharacterMapper
import javax.inject.Inject

class CharacterDetailInteractorImpl @Inject constructor(
    private val getCharacterByIdUseCaseImpl: GetCharacterByIdUseCase,
    private val keyValueRepository: KeyValueRepository,
    private val characterMapper: CharacterMapper
) : CharacterDetailInteractor() {

    override suspend fun getCharacterById(id: String): Character? {

        try {

            val characterResponse = getCharacterByIdUseCaseImpl.execute(id)
            return characterResponse?.let(characterMapper::map)?.get(0)

        } catch (ex: UseCaseException) {

            Log.e(
                this::class.simpleName,
                "Error on get character with id = $id - httpCode: ${ex.httpCode} - message: ${ex.message}"
            )
            return null
        }
    }

    override suspend fun saveFavoriteCharacterId(characterId: String): Boolean {

        return keyValueRepository.saveStringPreference(FAVORITE_CHARACTER_ID, characterId)
    }

    override suspend fun getFavoriteCharacterId(): String {

        return keyValueRepository.readStringPreference(FAVORITE_CHARACTER_ID, "")
    }

    private companion object {

        private const val FAVORITE_CHARACTER_ID = "favorite_character_id"
    }
}
