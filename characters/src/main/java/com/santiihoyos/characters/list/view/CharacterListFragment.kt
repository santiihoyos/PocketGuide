package com.santiihoyos.characters.list.view

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.santiihoyos.base.feature.abstracts.BaseFragment
import com.santiihoyos.base.extensions.gone
import com.santiihoyos.base.extensions.invisible
import com.santiihoyos.base.extensions.visible
import com.santiihoyos.characters.R
import com.santiihoyos.characters.detail.view.CharacterDetailActivityArgs
import com.santiihoyos.characters.di.CharactersComponent
import com.santiihoyos.characters.entity.Character
import com.santiihoyos.characters.list.view.adapter.CharacterRecyclerViewAdapter
import com.santiihoyos.characters.list.view.decorator.CharacterItemDecoration
import com.santiihoyos.characters.list.viewmodel.CharacterListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class CharacterListFragment : BaseFragment<CharacterListViewModel>() {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var characterAdapter: CharacterRecyclerViewAdapter

    /**
     * RecyclerView of characters items
     */
    private val recyclerView: RecyclerView? by lazy {

        view?.findViewById(R.id.characters_charactersFragment_recycler)
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
        setupRecyclerView()
        viewModel.isLoading.observe(viewLifecycleOwner, ::onLoadingChange)
    }

    private fun onCharacterClickItem(character: Character) {

        findNavController().navigate(
            R.id.action_characterListFragment_to_characterDetailActivity,
            CharacterDetailActivityArgs(character.id.toString(), character.name).toBundle()
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
    private fun setupRecyclerView() = recyclerView?.apply {

        val columnCount = resources.getInteger(R.integer.number_columns_character_list)
        layoutManager = when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }

        val adapterInstance = characterAdapter.apply {

            onCharacterClickListener = ::onCharacterClickItem
        }

        adapter = adapterInstance

        addItemDecoration(CharacterItemDecoration(
            resources.getDimension(R.dimen.character_list_item_margin).toInt(),
            columnCount
        ))

        setHasFixedSize(true)

        lifecycleScope.launch(Dispatchers.IO) {

            viewModel.loadNextCharacters().collectLatest(adapterInstance::submitData)
        }
    }
}
