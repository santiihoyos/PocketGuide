package com.santiihoyos.characters.detail.interactor

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.santiihoyos.api_rickandmorty.response.CharacterResponse
import com.santiihoyos.api_rickandmorty.response.LocationResponse
import com.santiihoyos.api_rickandmorty.usecase.GetCharacterByIdUseCase
import com.santiihoyos.base_api.repository.KeyValueRepository
import com.santiihoyos.base_api.usecase.UseCaseException
import com.santiihoyos.characters.entity.mappers.CharacterMapper
import com.santiihoyos.characters.entity.mappers.LocationMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

/**
 * Interactor layer tests
 */
@ExperimentalCoroutinesApi
class CharacterDetailInteractorImplTest {

    private val mockUseCase = mock<GetCharacterByIdUseCase>()
    private val mockKeyValueRepo = mock<KeyValueRepository>()
    private val mapper = CharacterMapper(LocationMapper()) //We need this for map CharacterResponse
    private val detailInteractorImpl = CharacterDetailInteractorImpl(mockUseCase, mockKeyValueRepo, mapper)

    @Test
    fun `check interactor return correct Character from ok response`() = runBlockingTest {

        whenever(mockUseCase.execute(any())).then {
            return@then characterResponse
        }

        val resultResponse = detailInteractorImpl.getCharacterById("1")
        Assert.assertEquals(resultResponse, mapper.map(characterResponse))
    }

    @Test
    fun `check interactor return null when UseCase launch use case exception`() = runBlockingTest {

        whenever(mockUseCase.execute(any())).thenThrow(UseCaseException(404))

        val resultResponse = detailInteractorImpl.getCharacterById("1")
        Assert.assertEquals(resultResponse, null)
    }

    private companion object {

        private val characterResponse = CharacterResponse(
            id = "1",
            name = "Rick",
            status = "Alive",
            species = "",
            type = "",
            gender = "Male",
            origin = "",
            location = LocationResponse(
                id = 0,
                name = "",
                created = "",
                dimension = "",
                residents = emptyList(),
                url = "",
                type = ""
            ),
            image = "image.com",
            episode = listOf("1", "2"),
            url = "",
            created = "",
        )
    }
}
