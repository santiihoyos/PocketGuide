package com.santiihoyos.characters.list.viewmodel

import androidx.paging.*
import com.santiihoyos.base_api.usecase.UseCaseException
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.characters.list.interactor.CharacterListInteractor
import kotlinx.coroutines.flow.*
import javax.inject.Inject

private const val SIZE_BY_PAGE = 10

class CharacterListViewModelImpl @Inject constructor(
    private val characterListInteractor: CharacterListInteractor
) : CharacterListViewModel() {

    override suspend fun loadNextCharacters(): Flow<PagingData<Character>> {

        val pagingSource = object : PagingSource<Int, Character>() {

            override fun getRefreshKey(state: PagingState<Int, Character>): Int? {

                return state.anchorPosition?.let { _anchorPosition ->

                    val anchorPage = state.closestPageToPosition(_anchorPosition)
                    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                }
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {

                val currentPageSource = params.key ?: 0

                return try {

                    //Call to interactor for get CharactersList
                    val result = characterListInteractor.getNextCharacters(currentPageSource)

                    return LoadResult.Page(
                        data = result ?: emptyList(),
                        prevKey = if (currentPageSource == 0) null else currentPageSource - 1,
                        nextKey = if (result.isNullOrEmpty()) null else currentPageSource + 1
                    )
                } catch (ex: UseCaseException) {

                    LoadResult.Error(ex)
                }
            }
        }

        return Pager(
            config = PagingConfig(pageSize = SIZE_BY_PAGE, enablePlaceholders = true),
            pagingSourceFactory = { pagingSource }
        ).flow
    }
}
