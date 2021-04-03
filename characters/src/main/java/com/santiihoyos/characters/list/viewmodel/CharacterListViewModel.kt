package com.santiihoyos.characters.list.viewmodel

import androidx.lifecycle.MutableLiveData
import com.santiihoyos.base.abstracts.BaseViewModel
import com.santiihoyos.characters.entity.Character

/**
 * Required contract by view layer (HeroListFragment)
 */
abstract class CharacterListViewModel: BaseViewModel() {

    abstract val heroes: MutableLiveData<List<Character>?>

    /**
     * Load or reload list of characters from new page.
     */
    abstract fun loadNextCharacters()
}
