package com.santiihoyos.characters.detail.view

import androidx.appcompat.widget.AppCompatTextView
import com.santiihoyos.characters.R
import com.santiihoyos.characters.entity.Character

class CharacterDetailActivityImpl: CharacterDetailActivity() {

    private val name by lazy { findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_name) }
    private val description by lazy { findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_description) }

    override fun paintCharacterValues(character: Character) {

        setupToolbar(character.name ?: "")
        name.text = character.name
        description.text = character.description
        refreshFavButtonState(character.id.toString())
    }

}
