package com.santiihoyos.characters.detail.view

import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.Group
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.santiihoyos.base.feature.abstracts.BaseActivity
import com.santiihoyos.base.feature.extensions.invisible
import com.santiihoyos.base.feature.extensions.loadFromUrl
import com.santiihoyos.base.feature.extensions.visible
import com.santiihoyos.characters.R
import com.santiihoyos.characters.detail.viewModel.CharacterDetailViewModel
import com.santiihoyos.characters.di.CharactersComponent
import com.santiihoyos.characters.entity.Character
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailActivity : BaseActivity<CharacterDetailViewModel>() {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val detailContainer: CoordinatorLayout by lazy {

        findViewById(R.id.characterDetailActivity_container_ConstraintLayout)
    }

    /**
     * Group of loading views
     */
    private val loadingGroup: Group by lazy {

        findViewById(R.id.characters_charactersActivity_loading_group)
    }

    /**
     * Full screen loading
     */
    private val photo: AppCompatImageView by lazy {

        findViewById(R.id.characterDetailActivity_photo_AppCompatImageView)
    }

    private val toolbar: Toolbar by lazy {

        findViewById(R.id.toolbar)
    }

    private val loadingText: AppCompatTextView by lazy {

        findViewById(R.id.characters_charactersFragment_loading_textView)
    }

    /**
     * Character.id from Bundle
     */
    private val characterId: String? by lazy {

        intent.extras?.let { _bundle ->

            CharacterDetailActivityArgs.fromBundle(_bundle).CHARACTERID
        }
    }

    override fun inject() = CharactersComponent.instance.inject(this)

    override fun getViewModelAbstract() = CharacterDetailViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)
        setupCharacterDetail()

        setSupportActionBar(toolbar)
    }

    /**
     * Paint Character instance returned from viewModel and refresh all data values.
     * This function suspends into Main coroutine until viewModel getCharacter coroutine returns
     * without block main thread :)
     */
    private fun setupCharacterDetail() = lifecycleScope.launch(Dispatchers.Main) {

        val characterIdFromBundle = characterId
        if (characterIdFromBundle == null) {

            showErrorRetryDialog()
        } else {

            loadingGroup.visible()
            detailContainer.invisible()
            val character = viewModel.getCharacter(characterIdFromBundle)
            if (character == null) {

                showErrorRetryDialog()
            } else {

                refreshDataValues(character)
            }
            detailContainer.visible()
            loadingGroup.invisible()
        }
    }

    /**
     * Refresh all data values.
     *
     * @param character - Character entity to paint
     */
    private fun refreshDataValues(character: Character) {

        //photo.loadFromUrl(character.image)

        Picasso.get().load(character.image)
            .placeholder(R.drawable.character_photo_placeholder)
            .fit().centerInside().into(photo)

        supportActionBar?.title = character.name
     }

    /**
     * Shows generic error retry dialog for character detail error on load
     */
    private fun showErrorRetryDialog() = showGenericErrorRetryDialog(
        message = R.string.characterDetailActivity_error_message,
        onOkClickListener = ::onOkErrorDialogClick,
        onCancelClickListener = ::onCancelErrorDialogClick
    )

    /**
     * Called when user taps into OK button into ErrorRetryDialog
     */
    private fun onOkErrorDialogClick() {

        dismissDialog()
    }

    /**
     * Called when user taps into CANCEL button into ErrorRetryDialog
     */
    private fun onCancelErrorDialogClick() {

        dismissDialog()
    }
}
