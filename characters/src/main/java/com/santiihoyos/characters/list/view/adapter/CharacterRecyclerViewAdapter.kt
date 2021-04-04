package com.santiihoyos.characters.list.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.santiihoyos.base.extensions.loadFromUrl
import com.santiihoyos.characters.R
import com.santiihoyos.characters.entity.Character

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 */
class CharacterRecyclerViewAdapter(
    var characters: List<Character>,
    val onCharacterClickListener: (Character) -> Unit
) : RecyclerView.Adapter<CharacterRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_character_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(character = characters[position])
    }

    override fun getItemCount(): Int = characters.size

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
            photo.loadFromUrl(character.image)
            itemView.setOnClickListener { onCharacterClickListener(character) }
        }
    }
}
