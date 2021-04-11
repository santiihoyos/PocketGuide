package com.santiihoyos.characters.detail.interactor

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.santiihoyos.api_marvel.response.CharacterData
import com.santiihoyos.api_marvel.response.CharacterResponse
import com.santiihoyos.api_marvel.response.ResponseContainer
import com.santiihoyos.api_marvel.response.ResponseWrapper
import com.santiihoyos.api_marvel.usecase.GetCharacterByIdUseCase
import com.santiihoyos.base_api.repository.KeyValueRepository
import com.santiihoyos.base_api.usecase.UseCaseException
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.characters.entity.mappers.CharacterMapper
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
    private val mapper = CharacterMapper() //We need this for map CharacterResponse
    private val detailInteractorImpl = CharacterDetailInteractorImpl(mockUseCase, mockKeyValueRepo, mapper)

    @Test
    fun `check interactor return correct Character from ok response`() = runBlockingTest {

        //Mocked use case output to compare interactor output
        whenever(mockUseCase.execute(any())).then {
            return@then ResponseWrapper(
                data = ResponseContainer(
                    results = mutableListOf(
                        CharacterData(
                            id = 666,
                            name = "Batman",  //XD
                            description = null,
                            thumbnail = null,
                            modified = null,
                            resourceUri = null
                        )
                    )
                )
            )
        }

        val resultResponse = detailInteractorImpl.getCharacterById("1")
        Assert.assertEquals(resultResponse, characterResponse)
    }

    @Test
    fun `check interactor return null when UseCase launch use case exception`() = runBlockingTest {

        whenever(mockUseCase.execute(any())).thenThrow(UseCaseException(404))

        val resultResponse = detailInteractorImpl.getCharacterById("1")
        Assert.assertEquals(resultResponse, null)
    }

    private companion object {

        private val characterResponse = Character(
            id = 666,
            name = "Batman",  //XD
            description = null,
            imageUrl = null,
            imageExtension = null
        )
    }
}
