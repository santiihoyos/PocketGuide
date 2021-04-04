package com.santiihoyos.characters.detail.view

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.santiihoyos.base.feature.abstracts.BaseActivity
import com.santiihoyos.base.feature.extensions.gone
import com.santiihoyos.base.feature.extensions.visible
import com.santiihoyos.characters.R
import com.santiihoyos.characters.detail.viewModel.CharacterDetailViewModel
import com.santiihoyos.characters.di.CharactersComponent
import com.santiihoyos.characters.entity.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailActivity : BaseActivity<CharacterDetailViewModel>() {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    /**
     * Full screen loading
     */
    private val progressBarLayout: LinearLayout by lazy {

        findViewById(R.id.characterDetail_general_progress)
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

            progressBarLayout.visible()
            val character = viewModel.getCharacter(characterIdFromBundle)
            if (character == null) {

                showErrorRetryDialog()
            } else {

                refreshDataValues(character)
            }
            progressBarLayout.gone()
        }
    }

    /**
     * Refresh all data values.
     *
     * @param character - Character entity to paint
     */
    private fun refreshDataValues(character: Character) {
        val nameTextView = findViewById<AppCompatTextView>(R.id.character_name)
        (character.name + " " + character.id).also(nameTextView::setText)
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
