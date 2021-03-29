package com.santiihoyos.heroes.list.view

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.santiihoyos.base.abstracts.BaseFragment
import com.santiihoyos.heroes.R
import com.santiihoyos.heroes.list.view.adapter.CharacterRecyclerViewAdapter
import com.santiihoyos.heroes.list.viewmodel.CharacterListViewModelContract
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class CharacterListFragment : BaseFragment<CharacterListViewModelContract>() {

    @Inject
    override lateinit var viewModel: CharacterListViewModelContract

    private var columnCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = 1
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = CharacterRecyclerViewAdapter(listOf())
            }
        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(columnCount: Int) = CharacterListFragment()
    }
}
