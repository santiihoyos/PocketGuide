@file:Suppress("LiftReturnOrAssignment")

package com.santiihoyos.characters.detail.interactor

import android.util.Log
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.base_repository.Mapper
import com.santiihoyos.repositoryrickandmorty.RickAndMortyRestRepository
import com.santiihoyos.repositoryrickandmorty.response.CharacterResponse
import kotlinx.coroutines.delay
import java.lang.Exception
import javax.inject.Inject

class CharacterDetailInteractorImpl @Inject constructor(
    private val restRepository: RickAndMortyRestRepository,
    private val characterMapper: Mapper<CharacterResponse, Character>
): CharacterDetailInteractor() {

    override suspend fun getCharacterById(id: String): Character? {

        //Simulate delay to show loadings views... TODO: remove!
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
        }
    }
}
