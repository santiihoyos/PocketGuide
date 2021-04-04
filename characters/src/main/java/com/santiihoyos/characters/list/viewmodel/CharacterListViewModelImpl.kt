package com.santiihoyos.characters.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.characters.list.interactor.CharacterListInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterListViewModelImpl @Inject constructor(
    private val characterListInteractor: CharacterListInteractor
) : CharacterListViewModel() {

    private var currentPage = 0

    override val characters: MutableLiveData<List<Character>?> = MutableLiveData()

    override fun loadNextCharacters() {
        viewModelScope.launch {

            isLoading.postValue(true)
            refreshCharactersValue()
            isLoading.postValue(false)
        }
    }

    override fun reloadCharacters() {
        viewModelScope.launch {

            isLoading.postValue(true)
            //characters.postValue(emptyList())
            //currentPage = 0
            refreshCharactersValue()
            isLoading.postValue(false)
        }
    }


    private suspend fun refreshCharactersValue() {

        val newCharacters = characterListInteractor.getNextCharacters(currentPage++)
        this.characters.postValue(
            mutableListOf(
                *characters.value?.toTypedArray() ?: arrayOf(),
                *newCharacters.toTypedArray()
            )
        )
        this@CharacterListViewModelImpl.characters.postValue(newCharacters.sortedBy { it.id })
    }
}
