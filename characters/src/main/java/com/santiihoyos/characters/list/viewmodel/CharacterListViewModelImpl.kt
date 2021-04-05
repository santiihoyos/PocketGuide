package com.santiihoyos.characters.list.viewmodel

import androidx.paging.PagingData
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.characters.list.interactor.CharacterListInteractor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterListViewModelImpl @Inject constructor(
    private val characterListInteractor: CharacterListInteractor
) : CharacterListViewModel() {

    override suspend fun loadNextCharacters(): Flow<PagingData<Character>> {

        return characterListInteractor.getNextCharacters()
    }
}
