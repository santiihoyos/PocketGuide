package com.santiihoyos.heroes.list.interactor

import com.santiihoyos.base.abstracts.BaseInteractor
import com.santiihoyos.data.repository.RestRepository
import com.santiihoyos.heroes.list.entity.Character
import com.santiihoyos.heroes.list.mappers.CharacterMapper
import javax.inject.Inject

class CharacterListInteractor @Inject constructor(
    private val restRepository: RestRepository
): BaseInteractor(), CharacterListInteractorContract {


    override fun getNextCharacters(page: Int): List<Character> {

        val mapper = CharacterMapper()
        return restRepository.getCharactersPage(page).results.map(mapper::map)
    }
}
