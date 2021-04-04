package com.santiihoyos.characters.list.interactor

import androidx.paging.*
import com.santiihoyos.base_repository.Mapper
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.repositoryrickandmorty.RickAndMortyRestRepository
import com.santiihoyos.repositoryrickandmorty.response.CharacterResponse
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject

private const val SIZE_BY_PAGE = 20

class CharacterListInteractorImpl @Inject constructor(
    private val rickAndMortyRestRepository: RickAndMortyRestRepository,
    private val characterMapper: Mapper<CharacterResponse, Character>
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
                    val charactersResponse = rickAndMortyRestRepository.getCharactersAtPage(pageSource)
                    return LoadResult.Page(
                        data = charactersResponse.results.map(characterMapper::map),
                        prevKey = if (pageSource == 1) null else pageSource - 1,
                        nextKey = if (charactersResponse.results.isEmpty()) null else pageSource + 1
                    )
                } catch (ex: Exception) {

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
