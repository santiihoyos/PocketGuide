package com.santiihoyos.heroes.list.viewmodel

import androidx.lifecycle.MutableLiveData
import com.santiihoyos.base.contracts.BaseViewModelContract
import com.santiihoyos.heroes.list.entity.Character

/**
 * Required contract by view layer (HeroListFragment)
 */
interface CharacterListViewModelContract: BaseViewModelContract {

    val heroes: MutableLiveData<List<Character>?>

    /**
     * Load or reload list of Heroes
     */
    suspend fun loadHeroes()
}
