package com.santiihoyos.characters.detail.interactor

import com.santiihoyos.base.feature.abstracts.BaseInteractor
import com.santiihoyos.characters.entity.Character

abstract class CharacterDetailInteractor: BaseInteractor() {

    /**
     * Gets from data repository one character by ID
     *
     * @param id - character required id,
     * if return value is null characters was not found
     */
    abstract suspend fun getCharacterById(id: String): Character?

    //all necessary by viewModel
}
