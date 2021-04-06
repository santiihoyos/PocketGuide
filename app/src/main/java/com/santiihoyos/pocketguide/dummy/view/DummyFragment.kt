package com.santiihoyos.pocketguide.dummy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.santiihoyos.base.feature.abstracts.BaseFragment
import com.santiihoyos.marvelguide.R
import com.santiihoyos.pocketguide.di.AppComponent
import com.santiihoyos.pocketguide.dummy.viewmodel.DummyViewModel
import javax.inject.Inject

class DummyFragment : BaseFragment<DummyViewModel>() {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    override fun inject() = AppComponent.instance.inject(this)

    override fun getViewModelAbstract(): Class<DummyViewModel> = DummyViewModel::class.java

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        textView.text = "Dummy fragment"
        return root
    }
}
