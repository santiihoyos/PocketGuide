package com.santiihoyos.characters.list.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.santiihoyos.characters.R
import com.santiihoyos.characters.entity.Character

/**
 * [RecyclerView.Adapter] that can display a [Character].
 */
open class CharacterRecyclerViewAdapter: PagingDataAdapter<Character, CharacterRecyclerViewAdapter.ViewHolder>(diffCallback) {

    open var onCharacterClickListener: ((character: Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_character_list_item, parent, false)
        return ViewHolder(view, onCharacterClickListener)
    }

    override fun onBindViewHolder(holder: CharacterRecyclerViewAdapter.ViewHolder, position: Int) {

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
    open inner class ViewHolder(
        view: View,
        private val onCharacterClickListener: ((character: Character) -> Unit)?
    ) : RecyclerView.ViewHolder(view) {

        /**
         * name of character
         */
        protected val name: AppCompatTextView = view.findViewById(R.id.characters_characterItem_name_textView)

        /**
         * Photo, loaded with Glide
         */
        protected val photo: AppCompatImageView = view.findViewById(R.id.characters_characterItem_imageView)

        /**
         * Binds holder view instances with current data at position
         */
        open fun bind(character: Character) {

            name.text = character.name
            itemView.setOnClickListener { onCharacterClickListener?.invoke(character) }
        }
    }
}
