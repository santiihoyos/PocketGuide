package com.santiihoyos.characters.detail.view

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.santiihoyos.base.extensions.loadFromUrl
import com.santiihoyos.characters.R
import com.santiihoyos.characters.entity.Character

class CharacterDetailActivityImpl: CharacterDetailActivity() {

    private val name by lazy { findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_name) }
    private val photo by lazy { findViewById<AppCompatImageView>(R.id.characterDetailActivity_photo_AppCompatImageView) }
    private val description by lazy { findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_description) }

    override fun paintCharacterValues(character: Character) {

        setupToolbar(character.name ?: "")
        name.text = character.name
        description.text = character.description
        photo.loadFromUrl(character.getImageDetailUrl() ?: "", R.drawable.character_photo_placeholder )
        refreshFavButtonState(character.id.toString())
    }

}
