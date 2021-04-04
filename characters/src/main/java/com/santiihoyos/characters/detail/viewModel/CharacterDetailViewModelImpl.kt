package com.santiihoyos.characters.detail.viewModel

import android.util.Log
import com.santiihoyos.characters.detail.interactor.CharacterDetailInteractor
import com.santiihoyos.characters.entity.Character
import javax.inject.Inject

class CharacterDetailViewModelImpl @Inject constructor(
    private val characterDetailInteractor: CharacterDetailInteractor
): CharacterDetailViewModel() {

    init {
        Log.i("INJECTADO", characterDetailInteractor.toString())
    }

    override suspend fun getCharacter(characterId: String): Character? {

        return characterDetailInteractor.getCharacterById(characterId)
    }
}
