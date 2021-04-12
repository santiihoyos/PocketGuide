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

    /**
     * Using KeyValueRepository resolve current
     * favorite character of user id
     *
     * @param characterId - id of Charater to save as favorite
     * @return Bolean true if save operation was ok
     */
    abstract suspend fun saveFavoriteCharacterId(characterId: String): Boolean

    /**
     * Using KeyValueRepository resolve current
     * favorite character of user id
     *
     * @return id of favorite character
     */
    abstract suspend fun getFavoriteCharacterId(): String

    //all necessary by viewModel
}
