package com.santiihoyos.characters.detail.view

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.Group
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
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
     * Group of loading views
     */
    private val loadingAppCompatTextView: AppCompatTextView by lazy {

        findViewById(R.id.characters_charactersFragment_loading_textView)
    }

    private val toolbar: Toolbar by lazy {

        findViewById(R.id.toolbar)
    }

    /**
     * Character.id from Bundle
     */
    private val characterId: String? by lazy {

        intent.extras?.let { _bundle ->

            CharacterDetailActivityArgs.fromBundle(_bundle).CHARACTERID
        }
    }

    /**
     * Heart button
     */
    private val favButton: AppCompatImageView by lazy {

        findViewById(R.id.characterDetailActivity_fav_AppCompatImageView)
    }

        /**
         * Character.id from Bundle
         */
        private var characterName: String? = null

        override fun inject() = CharactersComponent.instance.inject(this)

        override fun getViewModelAbstract() = CharacterDetailViewModel::class.java

        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_character_detail)
            setupCharacterDetail()
            setupToolbar("")
            characterName = intent.extras?.let { _bundle -> CharacterDetailActivityArgs.fromBundle(_bundle).CHARACTERNAME }
            favButton.setOnClickListener(::onFavButtonClick)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            return when (item.itemId) {
                android.R.id.home -> {
                    finish()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }

        /**
         * Paint Character instance returned from viewModel and refresh all data values.
         * This function suspends into Main coroutine until viewModel getCharacter coroutine returns
         * without block main thread :)
         */
        private fun setupCharacterDetail() = lifecycleScope.launch(Dispatchers.Main) {

            val characterIdFromBundle = characterId
            if (characterIdFromBundle == null) {

                showErrorRetryDialog(R.string.characterDetailActivity_error_message)
            } else {

                loadingAppCompatTextView.text = getString(R.string.characterDetailActivity_loading, characterName)
                loadingGroup.visible()
                detailContainer.invisible()
                val character = viewModel.getCharacter(characterIdFromBundle)
                if (character == null) {

                    showErrorRetryDialog(R.string.characterDetailActivity_error_message)
                } else {

                    refreshDataValues(character)
                }
                detailContainer.visible()
                loadingGroup.invisible()
            }
        }

        /**
         * Refresh with incoming [Character] instance all view data values.
         *
         * @param character - Character entity to paint
         */
        private fun refreshDataValues(character: Character) {

            setupToolbar(character.name)
            val photo = findViewById<AppCompatImageView>(R.id.characterDetailActivity_photo_AppCompatImageView)
            val status = findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_status)
            val statusBullet = findViewById<AppCompatImageView>(R.id.characterDetailActivity_value_status_bullet)
            val name = findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_name)
            val type = findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_type)
            val species = findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_species)
            val gender = findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_gender)
            val episodes = findViewById<AppCompatTextView>(R.id.characterDetailActivity_value_episodes)
            photo.loadFromUrl(character.image, R.drawable.character_photo_placeholder)
            name.text = character.name
            status.text = character.status.name.toLowerCase(Locale.getDefault())
            ImageViewCompat.setImageTintList(
                statusBullet,
                ColorStateList.valueOf(resolveStatusColor(character.status))
            )
            type.text = if (character.type.isEmpty()) "-" else character.type
            species.text = character.species
            gender.text = character.gender.name.toLowerCase(Locale.getDefault())
            episodes.text = character.episode.joinToString(separator = ", ") {
                it.substring(it.lastIndexOf("/") + 1)
            }

            refreshFavButtonState(character.id)
        }

        /**
         * Shows generic error retry dialog for character detail error on load
         */
        private fun showErrorRetryDialog(@StringRes messageResId: Int) = showGenericErrorRetryDialog(
            message = messageResId,
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

        /**
         * Resolves status color
         *
         * @param status [Character.status]
         * @return Int - Color as Int (not color res id)
         */
        private fun resolveStatusColor(status: Character.Status): Int {
            return ContextCompat.getColor(
                this,
                when (status) {
                    Character.Status.ALIVE -> R.color.characterDetail_status_alive
                    Character.Status.DEAD -> R.color.characterDetail_status_dead
                    Character.Status.UNKNOWN -> R.color.characterDetail_status_unknown
                }
            )
        }

        /**
         * Setups toolbar
         *
         * @param title - title (character name)
         */
        private fun setupToolbar(title: String) {

            toolbar.title = title
            toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white))
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }

        /**
         * setups fav buton
         */
        private fun refreshFavButtonState(characterId: String) {

            lifecycleScope.launch(Dispatchers.IO) {

                val isFavThisCharacter = viewModel.isUserCharacterFavorite(characterId)

                favButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@CharacterDetailActivity,
                        if (isFavThisCharacter) R.drawable.ic_heart_filled else R.drawable.ic_heart_empty
                    )
                )
            }
        }

    private fun onFavButtonClick(view: View) = characterId?.let { _characterId ->

        lifecycleScope.launch(Dispatchers.IO) {

            val saved =  viewModel.saveAsFavorite(_characterId)
            if (!saved) {

                showErrorRetryDialog(R.string.characterDetailActivity_error_save_fav_message)
            } else {

                refreshFavButtonState(_characterId)
            }
        }
    }
}
