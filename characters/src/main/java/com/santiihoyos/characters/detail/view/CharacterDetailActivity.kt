package com.santiihoyos.characters.detail.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.santiihoyos.base.abstracts.BaseActivity
import com.santiihoyos.characters.R
import com.santiihoyos.characters.detail.viewModel.CharacterDetailViewModel
import com.santiihoyos.characters.di.CharactersComponent
import javax.inject.Inject

class CharacterDetailActivity : BaseActivity<CharacterDetailViewModel>() {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    override fun inject() = CharactersComponent.instance.inject(this)

    override fun getViewModelAbstract() = CharacterDetailViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)
    }

    override fun onResume() {

        super.onResume()
        Log.i("VIEWMODEL", viewModel.toString())
    }
}
