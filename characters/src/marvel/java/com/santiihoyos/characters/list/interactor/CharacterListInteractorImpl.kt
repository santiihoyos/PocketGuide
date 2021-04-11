package com.santiihoyos.characters.list.interactor

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.santiihoyos.api_marvel.usecase.GetCharactersPagingUseCase
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.base_api.usecase.UseCaseException
import com.santiihoyos.characters.entity.mappers.CharacterMapper
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

            val currentPageSource = params.key ?: 0

            return try {

                val response = getCharactersPagingUseCaseImpl.execute(currentPageSource)
                val result = characterMapper.map(response)?.sortedBy { it.getPreviewImageUrl() }
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
}
