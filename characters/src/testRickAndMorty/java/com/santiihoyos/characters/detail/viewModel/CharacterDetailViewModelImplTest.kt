package com.santiihoyos.characters.detail.viewModel

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.santiihoyos.characters.detail.interactor.CharacterDetailInteractor
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.characters.entity.Location
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*

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
            return@then charactersMock.firstOrNull { it.id == idIndex.toString() }
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
                "1",
                "Rick",
                Character.Status.ALIVE,
                "",
                "",
                Character.Gender.MALE,
                "",
                Location(0, ""),
                "",
                listOf("1", "2")
            ),
            Character(
                "2",
                "Beth",
                Character.Status.ALIVE,
                "",
                "",
                Character.Gender.FEMALE,
                "",
                Location(0, ""),
                "",
                listOf("1", "2")
            ),
            Character(
                "3",
                "Morty",
                Character.Status.ALIVE,
                "",
                "",
                Character.Gender.MALE,
                "",
                Location(0, ""),
                "",
                listOf("1", "2")
            ),
            Character(
                "4",
                "Summer",
                Character.Status.ALIVE,
                "",
                "",
                Character.Gender.FEMALE,
                "",
                Location(0, ""),
                "",
                listOf("1", "2")
            ),
        )
    }
}
