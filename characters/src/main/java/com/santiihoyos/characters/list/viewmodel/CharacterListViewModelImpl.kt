package com.santiihoyos.characters.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.characters.list.interactor.CharacterListInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterListViewModelImpl @Inject constructor(
    private val characterListInteractor: CharacterListInteractor
): CharacterListViewModel() {

    private var currentPage = 0

    override val heroes: MutableLiveData<List<Character>?> = MutableLiveData()

    override fun loadNextCharacters() {
        viewModelScope.launch {

            val characters = characterListInteractor.getNextCharacters(++currentPage)
            heroes.postValue(characters.sortedBy { it.id })
        }
    }
}
