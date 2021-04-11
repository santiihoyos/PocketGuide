package com.santiihoyos.characters.detail.view

import android.content.res.ColorStateList
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.santiihoyos.base.extensions.loadFromUrl
import com.santiihoyos.characters.R
import com.santiihoyos.characters.entity.Character
import java.util.*

class CharacterDetailActivityImpl: CharacterDetailActivity() {

    private val photo by lazy { findViewById<AppCompatImageView>(R.id.characterDetailActivity_photo_AppCompatImageView) }
    private val status by lazy { findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_status) }
    private val statusBullet by lazy { findViewById<AppCompatImageView>(R.id.characterDetailActivity_value_status_bullet) }
    private val name by lazy { findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_name) }
    private val type by lazy { findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_type) }
    private val species by lazy { findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_species) }
    private val gender by lazy { findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_gender) }
    private val episodes by lazy { findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_episodes) }
    private val lastLoc by lazy { findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_lastLocation) }

    override fun paintCharacterValues(character: Character) {

        setupToolbar(character.name)
        photo.loadFromUrl(character.image, R.drawable.character_photo_placeholder)
        name.text = character.name
        status.text = character.status.name.toLowerCase(Locale.getDefault())
        ImageViewCompat.setImageTintList(
            statusBullet,
            ColorStateList.valueOf(resolveStatusColor(character.status))
        )
        type.text = if (character.type.isEmpty()) "-" else character.type
        species.text = character.species
        gender.text = character.gender.name.toLowerCase(Locale.getDefault())
        episodes.text = character.episode.joinToString(separator = ", ") {
            it.substring(it.lastIndexOf("/") + 1)
        }
        lastLoc.text = character.location?.name ?: "-"
        refreshFavButtonState(character.id)
    }

    /**
     * Resolves status color
     *
     * @param status [Character.status]
     * @return Int - Color as Int (not color res id)
     */
    private fun resolveStatusColor(status: Character.Status): Int {
        return ContextCompat.getColor(
            this,
            when (status) {
                Character.Status.ALIVE -> R.color.characterDetail_status_alive
                Character.Status.DEAD -> R.color.characterDetail_status_dead
                Character.Status.UNKNOWN -> R.color.characterDetail_status_unknown
            }
        )
    }

}
