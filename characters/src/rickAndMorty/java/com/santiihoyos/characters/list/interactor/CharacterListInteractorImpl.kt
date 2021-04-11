package com.santiihoyos.characters.list.interactor

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.santiihoyos.api_rickandmorty.usecase.GetCharactersPagingUseCase
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.base_api.usecase.UseCaseException
import com.santiihoyos.characters.entity.mappers.CharacterMapper
import kotlinx.coroutines.delay
import javax.inject.Inject

class CharacterListInteractorImpl @Inject constructor(
    private val getCharactersPagingUseCaseImpl: GetCharactersPagingUseCase,
    private val characterMapper: CharacterMapper
) : CharacterListInteractor() {

    override fun getCharactersPageSource() = object : PagingSource<Int, Character>() {

        override fun getRefreshKey(state: PagingState<Int, Character>): Int? {

            return state.anchorPosition?.let { _anchorPosition ->

                val anchorPage = state.closestPageToPosition(_anchorPosition)
                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
            }
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {

            val pageSource = params.key ?: 1

            return try {

                val results = getCharactersPagingUseCaseImpl.execute(pageSource).results.map(characterMapper::map)

                return LoadResult.Page(
                    data = results,
                    prevKey = if (pageSource == 1) null else pageSource - 1,
                    nextKey = if (results.isNullOrEmpty()) null else pageSource + 1
                )
            } catch (ex: UseCaseException) {

                LoadResult.Error(ex)
            }
        }
    }
}
