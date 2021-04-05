package com.santiihoyos.characters.list.viewmodel

import androidx.core.content.contentValuesOf
import androidx.paging.PagingData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.characters.entity.Location
import com.santiihoyos.characters.list.interactor.CharacterListInteractor
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.*
import org.junit.*

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CharacterListViewModelImplTest {

    private val charactersMock = listOf(
        Character("1", "Rick", Character.Status.ALIVE, "", "", Character.Gender.MALE, "", Location(0,""), "", listOf("1", "2")),
        Character("2", "Beth", Character.Status.ALIVE, "", "", Character.Gender.FEMALE, "", Location(0,""), "", listOf("1", "2")),
        Character("3", "Morty", Character.Status.ALIVE, "", "", Character.Gender.MALE, "", Location(0,""), "", listOf("1", "2")),
        Character("4", "Summer", Character.Status.ALIVE, "", "", Character.Gender.FEMALE, "", Location(0,""), "", listOf("1", "2")),
    )

    /**
     * Comprueba que la salida del interactor son las salidas del view model
     * en este caso no hay mas que comprobar porque no hay mas logica en esta
     * funcion del view model
     */
    @Test
    fun `loadNextCharacters ok flow with 2 pages`() = runBlockingTest {

        val currentResults = mutableListOf<PagingData<Character>>()
        val interactorMock = mock<CharacterListInteractor>()
        val viewModelMock = CharacterListViewModelImpl(interactorMock)
        val mockFlow = BroadcastChannel<PagingData<Character>>(1)
        val firstEmission = PagingData.from(listOf(charactersMock[0], charactersMock[1]))
        val secondEmission = PagingData.from(listOf(charactersMock[2], charactersMock[3]))
        val flow = mockFlow.asFlow()
        var callCount = 1

        val job = launch{ flow.collectLatest(currentResults::add) }

        //When interactor mock is called by viewModel we return mocked data
        //for first call we return firstEmission
        //for second call we return secondEmission
        whenever(interactorMock.getNextCharacters()).then {
            if (callCount == 1) {

                //offers first page with characters 1, 2
                mockFlow.offer(firstEmission)
            } else if (callCount == 2) {

                //offers first page with characters 2, 3
                mockFlow.offer(secondEmission)
            } else {

                mockFlow.offer(PagingData.empty())
            }

            callCount++
            return@then flow
        }

        //Launch first load and expect first emission in my container of collected
        viewModelMock.loadNextCharacters()
        assert(currentResults.contains(firstEmission))

        //Launch first load and expect second emission in my container of collected
        viewModelMock.loadNextCharacters()
        assert(currentResults.contains(secondEmission))

        //Launch first load and expect empty emission in my container of collected
        viewModelMock.loadNextCharacters()
        assert(currentResults.contains(PagingData.empty()))

        job.cancel()
    }
}

