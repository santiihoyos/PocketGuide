@file:Suppress("LiftReturnOrAssignment")

package com.santiihoyos.characters.detail.interactor

import android.util.Log
import com.santiihoyos.api_rickandmorty.response.CharacterResponse
import com.santiihoyos.api_rickandmorty.usecase.GetCharacterByIdUseCase
import com.santiihoyos.base_api.Mapper
import com.santiihoyos.base_api.repository.KeyValueRepository
import com.santiihoyos.base_api.usecase.UseCaseException
import com.santiihoyos.characters.entity.Character
import kotlinx.coroutines.delay
import javax.inject.Inject

class CharacterDetailInteractorImpl @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val keyValueRepository: KeyValueRepository,
    private val characterMapper: Mapper<CharacterResponse, Character>
) : CharacterDetailInteractor() {

    override suspend fun getCharacterById(id: String): Character? {

        //Simulate delay to show loadings views... TODO: remove!
        delay(500)

        try {

            val characterResponse = getCharacterByIdUseCase.execute(id)
            return characterMapper.map(characterResponse)

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
