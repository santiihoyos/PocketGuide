package com.santiihoyos.characters.detail.viewModel

import com.santiihoyos.characters.detail.interactor.CharacterDetailInteractor
import com.santiihoyos.characters.entity.Character
import javax.inject.Inject

class CharacterDetailViewModelImpl @Inject constructor(
    private val characterDetailInteractor: CharacterDetailInteractor
): CharacterDetailViewModel() {

    override suspend fun getCharacter(characterId: String): Character? {

        return characterDetailInteractor.getCharacterById(characterId)
    }

    override suspend fun saveAsFavorite(characterId: String): Boolean {

        return characterDetailInteractor.saveFavoriteCharacterId(characterId)
    }

    override suspend fun isUserCharacterFavorite(characterId: String): Boolean {

        val currentFavorite = characterDetailInteractor.getFavoriteCharacterId()
        return currentFavorite == characterId
    }
}
