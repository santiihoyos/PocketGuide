package com.santiihoyos.characters.list.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.santiihoyos.characters.R
import com.santiihoyos.characters.entity.Character

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 */
class CharacterRecyclerViewAdapter(
    var characters: List<Character>
) : RecyclerView.Adapter<CharacterRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_character_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = characters[position]
        holder.idView.text = item.name
        holder.contentView.text = item.species
    }

    override fun getItemCount(): Int = characters.size

    /**
     * Holder for Hero item
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}
