package com.santiihoyos.heroes.list.interactor

import com.santiihoyos.base.contracts.BaseInteractorContract
import com.santiihoyos.heroes.list.entity.Character

/**
 * Required Contract by viewModel layer
 */
interface CharacterListInteractorContract : BaseInteractorContract {

    /**
     *  get characters page (20 character objects by page)
     */
    fun getNextCharacters(page: Int): List<Character>

}
