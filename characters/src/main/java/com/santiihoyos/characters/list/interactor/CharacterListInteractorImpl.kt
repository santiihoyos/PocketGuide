package com.santiihoyos.characters.list.interactor

import com.santiihoyos.base_repository.Mapper
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.repositoryrickandmorty.RickAndMortyRestRepository
import com.santiihoyos.repositoryrickandmorty.response.CharacterResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class CharacterListInteractorImpl @Inject constructor(
    private val restRepository: RickAndMortyRestRepository,
    private val characterMapper: Mapper<CharacterResponse, Character>
): CharacterListInteractor() {


    override suspend fun getNextCharacters(page: Int): List<Character> {
        delay(2000)
        return restRepository.getCharactersAtPage(page).results.map(characterMapper::map)
    }
}
