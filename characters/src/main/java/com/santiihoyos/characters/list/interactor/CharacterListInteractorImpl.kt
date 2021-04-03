package com.santiihoyos.characters.list.interactor

import com.santiihoyos.data.repository.RestRepository
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.data.Mapper
import com.santiihoyos.data.response.CharacterResponse
import javax.inject.Inject

class CharacterListInteractorImpl @Inject constructor(
    private val restRepository: RestRepository,
    private val characterMapper: Mapper<CharacterResponse, Character>
): CharacterListInteractor() {


    override suspend fun getNextCharacters(page: Int): List<Character> {

        return restRepository.getCharactersAtPage(page).results.map(characterMapper::map)
    }
}
