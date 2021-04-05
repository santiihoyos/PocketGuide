package com.santiihoyos.characters.detail.interactor

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.santiihoyos.api_rickandmorty.response.CharacterResponse
import com.santiihoyos.api_rickandmorty.usecase.GetCharacterByIdUseCase
import com.santiihoyos.base_api.repository.KeyValueRepository
import com.santiihoyos.base_api.usecase.UseCaseException
import com.santiihoyos.characters.entity.mappers.CharacterMapper
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import com.santiihoyos.characters.entity.Character as Character2

class CharacterDetailInteractorImplTest {

    private val characterAsResponse = CharacterResponse("1", "Rick", "Alive", "", "", "Male", "", "", "image.com", listOf("1", "2"), "", "")
    private val mockUseCase = mock<GetCharacterByIdUseCase>()
    private val mockKeyValueRepo = mock<KeyValueRepository>()
    private val mapper = CharacterMapper() //this we need for map CharacterResponse
    private val detailInteractorImpl = CharacterDetailInteractorImpl(mockUseCase, mockKeyValueRepo, mapper)

    @Test
    fun `check interactor return correct Character from ok response`() = runBlockingTest {

        whenever(mockUseCase.execute(any())).then {
            return@then characterAsResponse
        }

        val resultResponse = detailInteractorImpl.getCharacterById("1")
        Assert.assertEquals(resultResponse, CharacterMapper().map(characterAsResponse))
    }

    @Test
    fun `check interactor return null when UseCase launch use case exception`() = runBlockingTest {

        whenever(mockUseCase.execute(any())).thenThrow(UseCaseException(404))

        val resultResponse = detailInteractorImpl.getCharacterById("1")
        Assert.assertEquals(resultResponse, null)
    }
}
