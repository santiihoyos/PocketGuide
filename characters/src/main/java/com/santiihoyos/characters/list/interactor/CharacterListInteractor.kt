package com.santiihoyos.characters.list.interactor

import androidx.paging.PagingData
import com.santiihoyos.base.feature.abstracts.BaseInteractor
import com.santiihoyos.characters.entity.Character
import kotlinx.coroutines.flow.Flow

/**
 * Required Contract by viewModel layer
 */
abstract class CharacterListInteractor: BaseInteractor() {

    /**
     *  get characters page (20 character objects by page)
     */
    abstract suspend fun getNextCharacters(): Flow<PagingData<Character>>

}
