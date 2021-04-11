package com.santiihoyos.characters.list.view

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.santiihoyos.base.feature.abstracts.BaseFragment
import com.santiihoyos.base.extensions.gone
import com.santiihoyos.base.extensions.invisible
import com.santiihoyos.base.extensions.visible
import com.santiihoyos.base.feature.abstracts.BaseActivity
import com.santiihoyos.characters.R
import com.santiihoyos.characters.detail.view.CharacterDetailActivityImplArgs
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

    /**
     * Recycler view adapter for characters list item
     */
    @Inject
    lateinit var characterAdapter: CharacterRecyclerViewAdapter

    /**
     * RecyclerView of characters items
     */
    private val recyclerView: RecyclerView? by lazy {

        view?.findViewById(R.id.characters_charactersFragment_recycler)
    }

    /**
     * ProgressBar of characters items
     */
    private val progressBar: ProgressBar? by lazy {

        view?.findViewById(R.id.characters_charactersFragment_progress)
    }

    /**
     * SwipeRefreshLayout of characters items
     */
    private val swipeRefreshLayout: SwipeRefreshLayout? by lazy {

        view?.findViewById(R.id.characters_charactersFragment_swipeRefreshLayout)
    }

    /**
     * Group to show when api data comes empty
     */
    private val emptyGroup: Group? by lazy {

        view?.findViewById(R.id.characters_characterFragment_group_empty)
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
        showLoading()
        setupRecyclerView()
        setupRefreshLayout()
        bindPageSourceToAdapter()
    }

    /**
     * Listen click on list intem
     *
     * suppress are for quick fix between flavors.
     */
    @Suppress("RemoveRedundantCallsOfConversionMethods")
    private fun onCharacterClickItem(character: Character) {

        findNavController().navigate(
            R.id.action_characterListFragment_to_characterDetailActivity,
            CharacterDetailActivityImplArgs(character.id.toString(), character.name).toBundle()
        )
    }

    /**
     * Show loading components
     */
    private fun showLoading() {

        progressBar.visible()
        recyclerView.invisible()
    }


    /**
     * hide loading components
     */
    private fun hideLoading() {

        progressBar.gone()
        recyclerView.visible()
    }

    /**
     * Setups recycler view with number of columns
     * and call viewModel for get data
     */
    private fun setupRecyclerView() = recyclerView?.apply {

        val columnCount = resources.getInteger(R.integer.number_columns_character_list)
        layoutManager = when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }

        val adapterInstance = characterAdapter.apply {


        }

        adapter = adapterInstance

        addItemDecoration(
            CharacterItemDecoration(
                resources.getDimension(R.dimen.character_list_item_margin).toInt(),
                columnCount
            )
        )

        setHasFixedSize(true)
    }

    private fun bindPageSourceToAdapter() = lifecycleScope.launch(Dispatchers.IO) {

        characterAdapter.onCharacterClickListener = ::onCharacterClickItem
        lifecycleScope.launch(Dispatchers.Main) {

            viewModel.loadNextCharacters().collectLatest(characterAdapter::submitData)
        }
        lifecycleScope.launch(Dispatchers.Main) {

            characterAdapter.loadStateFlow.collectLatest(::listenCharacterListAdapter)
        }
    }

    private fun listenCharacterListAdapter(loadState: CombinedLoadStates) {

        Log.i("LoadState", loadState.refresh.toString())

        when (loadState.refresh) {

            is LoadState.Error -> (activity as BaseActivity<*>).showGenericErrorRetryDialog(
                onOkClickListener = { characterAdapter.refresh() },
                message = R.string.characterListFragment_error_message,
                okText = R.string.retry
            )

            is LoadState.Loading -> {

                emptyGroup.invisible()
                showLoading()
            }

            is LoadState.NotLoading -> {
                hideLoading()

                //After first load results were empty
                if (characterAdapter.itemCount == 0) {

                    emptyGroup.visible()

                }
            }
        }
    }

    private fun setupRefreshLayout() {

        swipeRefreshLayout?.setOnRefreshListener {

            swipeRefreshLayout?.isRefreshing = false
            characterAdapter.refresh()
        }
    }
}
