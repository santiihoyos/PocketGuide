package com.santiihoyos.characters.list.viewmodel

import androidx.paging.*
import com.santiihoyos.base_api.usecase.UseCaseException
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.characters.list.interactor.CharacterListInteractor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val SIZE_BY_PAGE = 20

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

                val pageSource = params.key ?: 1

                return try {

                    val results = characterListInteractor.getNextCharacters(pageSource)
                    return LoadResult.Page(
                        data = results ?: emptyList(),
                        prevKey = if (pageSource == 1) null else pageSource - 1,
                        nextKey = if (results.isNullOrEmpty()) null else pageSource + 1
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
