package com.santiihoyos.characters.list.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santiihoyos.base.extensions.loadFromUrl
import com.santiihoyos.characters.R
import com.santiihoyos.characters.entity.Character
import javax.inject.Inject

/**
 * [RecyclerView.Adapter] that can display a [Character] of RickAndMorty Api.
 */
internal class CharacterRecyclerViewAdapterImpl @Inject constructor() : CharacterRecyclerViewAdapter()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterRecyclerViewAdapterImpl.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_character_list_item, parent, false)
        return this.ViewHolder(view, onCharacterClickListener)
    }

    /**
     * Override for bind because image comes from different apis
     */
    internal inner class ViewHolder(
        view: View,
        onCharacterClickListener: ((character: Character) -> Unit)?
    ) : CharacterRecyclerViewAdapter.ViewHolder(view, onCharacterClickListener){

        override fun bind(character: Character) {
            super.bind(character)

            photo.loadFromUrl(
                imageUrl = character.image,
                placeHolder = R.drawable.character_photo_placeholder
            )
        }
    }
}
