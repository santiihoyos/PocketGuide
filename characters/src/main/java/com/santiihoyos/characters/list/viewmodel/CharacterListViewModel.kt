package com.santiihoyos.characters.list.viewmodel

import androidx.paging.PagingData
import com.santiihoyos.base.feature.abstracts.BaseViewModel
import com.santiihoyos.characters.entity.Character
import kotlinx.coroutines.flow.Flow

/**
 * Required contract by view layer (HeroListFragment)
 */
abstract class CharacterListViewModel: BaseViewModel() {

    /**
     * Load or reload list of characters from new page.
     */
    abstract suspend fun loadNextCharacters(): Flow<PagingData<Character>>
}
