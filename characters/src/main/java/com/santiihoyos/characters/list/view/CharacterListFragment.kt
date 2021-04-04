package com.santiihoyos.characters.list.view

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.santiihoyos.base.abstracts.BaseFragment
import com.santiihoyos.base.extensions.gone
import com.santiihoyos.base.extensions.invisible
import com.santiihoyos.base.extensions.visible
import com.santiihoyos.characters.R
import com.santiihoyos.characters.detail.CharacterDetailActivityArgs
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
    private val recyclerView: RecyclerView? by lazy {

        view?.findViewById(R.id.characters_charactersFragment_recycler)
    }

    /**
     * SwipeRefreshLayout of characters items
     */
    private val swipeRefreshLayout: SwipeRefreshLayout? by lazy {

        view?.findViewById(R.id.characters_charactersFragment_swipeRefresh)
    }

    /**
     * SwipeRefreshLayout of characters items
     */
    private val loadingGroup: Group? by lazy {

        view?.findViewById(R.id.characters_charactersFragment_loadingGroup)
    }

    override fun getViewModelAbstract() = CharacterListViewModel::class.java

    override fun inject() = CharactersComponent.instance.inject(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_character_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(4)
        setupSwipeRefreshLayout()
        viewModel.loadNextCharacters()
        viewModel.characters.observe(viewLifecycleOwner, ::refreshCharactersList)
        viewModel.isLoading.observe(viewLifecycleOwner, ::onLoadingChange)
    }

    /**
     * Set new characters objects to recycler View on notified new values
     *
     * @param newCharacters List of Character with new characters to show
     */
    private fun refreshCharactersList(newCharacters: List<Character>?) {

        if (newCharacters.isNullOrEmpty()) {

            recyclerView.invisible()
        } else {

            (recyclerView?.adapter as? CharacterRecyclerViewAdapter)?.run {

                characters = newCharacters
                notifyDataSetChanged()
            }
        }
    }

    private fun onCharacterClickItem(character: Character) {


        findNavController().navigate(
            R.id.action_characterListFragment_to_characterDetailActivity,
            CharacterDetailActivityArgs(character.id).toBundle()
        )
    }

    /**
     * Hook called when viewModel changes its loading status
     */
    private fun onLoadingChange(isViewModelBusy: Boolean) {

        if (isViewModelBusy) {

            showLoading()
        } else {

            hideLoading()
        }
    }

    /**
     * View actions when user swipes to refresh
     */
    private fun onSwipeToRefresh() {

        viewModel.reloadCharacters()
        swipeRefreshLayout?.isRefreshing = false
    }

    /**
     * Show loading components
     */
    private fun showLoading() {

        loadingGroup.visible()
        recyclerView.invisible()
    }


    /**
     * hide loading components
     */
    private fun hideLoading() {

        loadingGroup.gone()
        recyclerView.visible()
    }

    /**
     * Setups recycler view with number of columns required by user
     */
    private fun setupRecyclerView(columnCount: Int) = recyclerView?.apply {

        layoutManager = when {

            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }
        adapter = CharacterRecyclerViewAdapter(
            characters = listOf(),
            onCharacterClickListener = ::onCharacterClickItem
        )
    }


    /**
     * Setups SwipeRefreshLayout listener
     */
    private fun setupSwipeRefreshLayout() {

        swipeRefreshLayout?.setOnRefreshListener(::onSwipeToRefresh)
    }

    /**
     * Show error dialog with retry option to user
     */
    private fun onError() {

    }
}
