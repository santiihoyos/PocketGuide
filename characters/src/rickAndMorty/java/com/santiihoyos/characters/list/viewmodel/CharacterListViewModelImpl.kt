package com.santiihoyos.characters.list.viewmodel

import androidx.paging.*
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.characters.list.interactor.CharacterListInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val SIZE_BY_PAGE = 20

class CharacterListViewModelImpl @Inject constructor(
    private val characterListInteractor: CharacterListInteractor
) : CharacterListViewModel() {

    override suspend fun loadNextCharacters(): Flow<PagingData<Character>> {

        return Pager(
            config = PagingConfig(pageSize = SIZE_BY_PAGE, enablePlaceholders = true),
            pagingSourceFactory = characterListInteractor::getCharactersPageSource
        ).flow
    }
}
