package com.santiihoyos.characters.list.interactor

import androidx.paging.PagingSource
import com.santiihoyos.base.feature.abstracts.BaseInteractor
import com.santiihoyos.characters.entity.Character

/**
 * Required Contract by viewModel layer
 */
abstract class CharacterListInteractor: BaseInteractor() {

    /**
     *  get characters page (20 character objects by page)
     */
    abstract fun getCharactersPageSource(): PagingSource<Int, Character>

}
