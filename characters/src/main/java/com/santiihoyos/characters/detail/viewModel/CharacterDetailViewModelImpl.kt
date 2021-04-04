package com.santiihoyos.characters.detail.viewModel

import android.util.Log
import com.santiihoyos.characters.detail.interactor.CharacterDetailInteractor
import javax.inject.Inject

class CharacterDetailViewModelImpl @Inject constructor(
    private val characterDetailInteractor: CharacterDetailInteractor
): CharacterDetailViewModel() {

    init {
        Log.i("INJECTADO", characterDetailInteractor.toString())
    }

}
