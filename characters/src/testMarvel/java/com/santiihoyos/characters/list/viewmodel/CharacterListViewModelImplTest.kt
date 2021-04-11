package com.santiihoyos.characters.list.viewmodel

import androidx.paging.PagingData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.santiihoyos.characters.entity.Character
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

@FlowPreview
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CharacterListViewModelImplTest {

    /**
     * Comprueba que la salida del interactor son las salidas del view model
     * en este caso no hay mas que comprobar porque no hay mas lógica en esta
     * función del view model
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

        val job = launch { flow.collectLatest(currentResults::add) }

        //When interactor mock is called by viewModel we return mocked data
        //for first call we return firstEmission
        //for second call we return secondEmission
        whenever(interactorMock.getNextCharacters()).then {

            when (callCount) {
                1 -> mockFlow.offer(firstEmission) //offers first page with characters 1, 2
                2 -> mockFlow.offer(secondEmission) //offers first page with characters 2, 3
                else -> mockFlow.offer(PagingData.empty())
            }
            callCount++
            return@then flow
        }

        //Launch first load and expect first emission in my container of collected
        viewModelMock.loadNextCharacters()
        assert(currentResults.contains(firstEmission))

        //Launch second load and expect second emission in my container of collected
        viewModelMock.loadNextCharacters()
        assert(currentResults.contains(secondEmission))

        //Launch other load and expect empty emission in my container of collected
        viewModelMock.loadNextCharacters()
        assert(currentResults.contains(PagingData.empty()))

        job.cancel()
    }

    private companion object {

        private val charactersMock = listOf(
            Character(
                id = 1,
                name = "Quick Silver",
                description = null,
                imageUrl = null,
                imageExtension = null
            ),
            Character(
                id = 2,
                name = "IronMan",
                description = null,
                imageUrl = null,
                imageExtension = null
            ),
            Character(
                id = 3,
                name = "BlackPanther",
                description = null,
                imageUrl = null,
                imageExtension = null
            ),
            Character(
                id = 4,
                name = "Scarlet which",
                description = null,
                imageUrl = null,
                imageExtension = null
            ),
        )
    }
}

