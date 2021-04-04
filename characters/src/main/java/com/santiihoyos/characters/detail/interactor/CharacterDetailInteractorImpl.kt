package com.santiihoyos.characters.detail.interactor

import android.util.Log
import com.santiihoyos.characters.detail.interactor.CharacterDetailInteractor
import com.santiihoyos.data.repository.RestRepository
import javax.inject.Inject

class CharacterDetailInteractorImpl @Inject constructor(
    private val restRepository: RestRepository
): CharacterDetailInteractor() {

    init {
        Log.i("INJECTADO", restRepository.toString())
    }

}
