package com.santiihoyos.characters.list.interactor

import androidx.paging.*
import com.santiihoyos.api_rickandmorty.usecase.GetCharactersPagingUseCase
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.base_api.usecase.UseCaseException
import com.santiihoyos.characters.entity.mappers.CharacterMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val SIZE_BY_PAGE = 20

class CharacterListInteractorImpl @Inject constructor(
    private val getCharactersPagingUseCaseImpl: GetCharactersPagingUseCase,
    private val characterMapper: CharacterMapper
) : CharacterListInteractor() {

    override suspend fun getNextCharacters(): Flow<PagingData<Character>> {

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
                    val response = getCharactersPagingUseCaseImpl.execute(pageSource)
                    return LoadResult.Page(
                        data = response.results.map(characterMapper::map),
                        prevKey = if (pageSource == 1) null else pageSource - 1,
                        nextKey = if (response.results.isEmpty()) null else pageSource + 1
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
