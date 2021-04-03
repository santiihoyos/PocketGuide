package com.santiihoyos.characters.list.view

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.santiihoyos.base.abstracts.BaseFragment
import com.santiihoyos.characters.R
import com.santiihoyos.characters.di.CharactersComponent
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.characters.list.view.adapter.CharacterRecyclerViewAdapter
import com.santiihoyos.characters.list.viewmodel.CharacterListViewModel
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class CharacterListFragment : BaseFragment<CharacterListViewModel>() {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    /**
     * RecyclerView of characters items
     */
    private val charactersRecyclerView: RecyclerView? by lazy {

        view?.findViewById(R.id.characters_charactersFragment_recycler)
    }

    override fun getViewModelAbstract() = CharacterListViewModel::class.java

    override fun inject() = CharactersComponent.instance.inject(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_character_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(1)
        viewModel.loadNextCharacters()
        viewModel.heroes.observe(viewLifecycleOwner, ::refreshCharactersList)
    }

    /**
     * Set new characters objects to recycler View on notified new values
     *
     * @param newCharacters List of Character with new characters to show
     */
    private fun refreshCharactersList(newCharacters: List<Character>?) {

        if (newCharacters.isNullOrEmpty()) {

            //TODO: show empty info
        } else {

            (charactersRecyclerView?.adapter as? CharacterRecyclerViewAdapter)?.run {

                characters = newCharacters
                notifyDataSetChanged()
            }
        }
    }

    private fun onLoadingChange(isCurrentLoading: Boolean) {

    }

    /**
     * Setups recycler view with number of columns required by user
     */
    private fun setupRecyclerView(columnCount: Int) = charactersRecyclerView?.apply {

        layoutManager = when {

            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }
        adapter = CharacterRecyclerViewAdapter(listOf())
    }

    /**
     * Show error dialog with retry option to user
     */
    private fun onError() {

    }
}
