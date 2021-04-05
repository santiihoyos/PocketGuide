@file:Suppress("LiftReturnOrAssignment")

package com.santiihoyos.characters.detail.interactor

import android.util.Log
import com.santiihoyos.api_rickandmorty.response.CharacterResponse
import com.santiihoyos.base_api.repository.KeyValueRepository
import com.santiihoyos.characters.entity.Character
import kotlinx.coroutines.delay
import java.lang.Exception
import javax.inject.Inject

class CharacterDetailInteractorImpl @Inject constructor(
    //private val restRepository: RickAndMortyRestRepository,
    private val keyValueRepository: KeyValueRepository,
    //private val characterMapper: Mapper<CharacterResponse, Character>
) : CharacterDetailInteractor() {

    override suspend fun getCharacterById(id: String): Character? {

/*        //Simulate delay to show loadings views... TODO: remove!
        delay(500)

        try {

            val characterResponse: CharacterResponse? = restRepository.getCharacterById(id)
            if (characterResponse == null) {

                return null
            } else {

                return characterMapper.map(characterResponse)
            }
        } catch (ex: Exception) { //TODO: add use case layer to manage errors

            Log.e(this::class.simpleName, "Error on get character with id = $id")
            return null
        }*/

        TODO("not fixed yet")
    }

    override suspend fun saveFavoriteCharacterId(characterId: String): Boolean {

        return false //keyValueRepository.saveStringPreference(FAVORITE_CHARACTER_ID, characterId)
    }

    override suspend fun getFavoriteCharacterId(): String {

        return "" //keyValueRepository.readStringPreference(FAVORITE_CHARACTER_ID, "")
    }

    private companion object {

        private const val FAVORITE_CHARACTER_ID = "favorite_character_id"
    }
}
