package com.santiihoyos.characters.list.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.santiihoyos.base.feature.extensions.loadFromUrl
import com.santiihoyos.characters.R
import com.santiihoyos.characters.entity.Character

/**
 * [RecyclerView.Adapter] that can display a [Character].
 */
class CharacterRecyclerViewAdapter(
    val onCharacterClickListener: (Character) -> Unit
) : PagingDataAdapter<Character, CharacterRecyclerViewAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_character_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        getItem(position)?.let(holder::bind)
    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<Character>() {

            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {

                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {

                return oldItem == newItem
            }
        }
    }

    /**
     * Holder for Character item
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        /**
         * name of character
         */
        private val name: AppCompatTextView = view.findViewById(R.id.characters_characterItem_name_textView)

        /**
         * Photo, loaded with Glide
         */
        private val photo: AppCompatImageView = view.findViewById(R.id.characters_characterItem_imageView)

        /**
         * Binds holder view instances with current data at position
         */
        fun bind(character: Character) {

            name.text = character.name
            photo.loadFromUrl(character.image, R.drawable.character_photo_placeholder)
            itemView.setOnClickListener { onCharacterClickListener(character) }
        }
    }
}
