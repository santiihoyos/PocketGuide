package com.santiihoyos.characters.detail.viewModel

import com.santiihoyos.base.feature.abstracts.BaseViewModel
import com.santiihoyos.characters.entity.Character

/**
 *
 * @author @SantiiHoyos
 */
abstract class CharacterDetailViewModel: BaseViewModel() {

    /**
     * gets character by id from data layer although injected interactor
     *
     * @param characterId - String
     */
    abstract suspend fun getCharacter(characterId: String): Character?

}
