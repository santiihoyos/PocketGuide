package com.santiihoyos.characters.list.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.characters.list.interactor.CharacterListInteractor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterListViewModelImpl @Inject constructor(
    private val characterListInteractor: CharacterListInteractor
) : CharacterListViewModel() {

    override var currentCharactersResult: Flow<PagingData<Character>>? = null
        private set

    override suspend fun loadNextCharacters(): Flow<PagingData<Character>> {

        val result = characterListInteractor.getNextCharacters().cachedIn(viewModelScope)
        currentCharactersResult = result
        return result
    }
}
