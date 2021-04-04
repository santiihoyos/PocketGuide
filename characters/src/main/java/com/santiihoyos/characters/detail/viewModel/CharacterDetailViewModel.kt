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

    /**
     * Save current character id as favorite
     *
     * @param characterId - id of Charater to save as favorite
     * @return Bolean true if save operation was ok
     */
    abstract suspend fun saveAsFavorite(characterId: String): Boolean

    /**
     * Check if passed id is of favorite character of user.
     *
     * @param characterId - id of Character on checking
     * @return Bolean is a result for is favorite
     */
    abstract suspend fun isUserCharacterFavorite(characterId: String) : Boolean
}
