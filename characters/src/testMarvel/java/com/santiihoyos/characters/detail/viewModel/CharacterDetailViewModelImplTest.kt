package com.santiihoyos.characters.detail.viewModel

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.santiihoyos.characters.detail.interactor.CharacterDetailInteractor
import com.santiihoyos.characters.entity.Character
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CharacterDetailViewModelImplTest {

    /**
     * Comprueba las salidas del viewModel en función del la intrada desde el interactor
     */
    @Test
    @ExperimentalCoroutinesApi
    fun `test viewModel returns interactor input and test use case exceptions`() = runBlockingTest {

        val interactorMock = mock<CharacterDetailInteractor>()
        val viewModel = CharacterDetailViewModelImpl(interactorMock)
        var idIndex = 0

        whenever(interactorMock.getCharacterById(any())).then {

            idIndex++
            return@then charactersMock.firstOrNull { it.id == idIndex }
        }

        val result1 = viewModel.getCharacter("3")
        assertEquals(charactersMock[0], result1)


        val result2 = viewModel.getCharacter("3")
        assertEquals(charactersMock[1], result2)


        val result3 = viewModel.getCharacter("3")
        assertEquals(charactersMock[2], result3)
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
            )
        )
    }
}
