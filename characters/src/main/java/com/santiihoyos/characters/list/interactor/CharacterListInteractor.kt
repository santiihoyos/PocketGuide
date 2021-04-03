package com.santiihoyos.characters.list.interactor

import com.santiihoyos.base.abstracts.BaseInteractor
import com.santiihoyos.characters.entity.Character

/**
 * Required Contract by viewModel layer
 */
abstract class CharacterListInteractor: BaseInteractor() {

    /**
     *  get characters page (20 character objects by page)
     */
    abstract suspend fun getNextCharacters(page: Int): List<Character>

}
