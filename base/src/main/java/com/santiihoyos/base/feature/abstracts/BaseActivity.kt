package com.santiihoyos.base.feature.abstracts

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.santiihoyos.base.R

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    /**
     * ViewModelProvider.Factory to resolve viewModel dependency
     */
    protected abstract val viewModelProviderFactory: ViewModelProvider.Factory

    private var currentGenericRetryErrorDialog: AlertDialog? = null

    @Suppress("UNCHECKED_CAST")
    protected val viewModel: T by lazy {

        ViewModelProvider(this, viewModelProviderFactory).get(getViewModelAbstract())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    /**
     * Inject current fragment using module
     * component.
     */
    protected abstract fun inject()

    /**
     * Resolve ViewModel abstract class, necessary to inject this view
     */
    protected abstract fun getViewModelAbstract(): Class<T>

    /**
     * Shows to user AlertDialog with info about.
     *
     * @param message - String res id with message text, could be null.
     * @param onOkClickListener - calback when user taps into OK button
     * @param onCancelClickListener - calback when user taps into CANCEL button
     */
    public fun showGenericErrorRetryDialog(
        @StringRes message: Int?,
        @StringRes okText: Int? = null,
        onOkClickListener: () -> Unit,
        onCancelClickListener: (() -> Unit)? = null
    ) {

        val builder = AlertDialog.Builder(this)
        builder.setMessage(message ?: R.string.baseActivity_generic_error_message)
            .setPositiveButton(okText ?: android.R.string.ok) { _, _ -> onOkClickListener() }

        if (onCancelClickListener != null) {

            builder.setNegativeButton(android.R.string.cancel) { _, _ -> onCancelClickListener() }
        }

        currentGenericRetryErrorDialog = builder.create()
        currentGenericRetryErrorDialog!!.show()
    }


    protected fun dismissDialog() {

        currentGenericRetryErrorDialog?.dismiss()
    }

    //more common logic for activities here...

}
