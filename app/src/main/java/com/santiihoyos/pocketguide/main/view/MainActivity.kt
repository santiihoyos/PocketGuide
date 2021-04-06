package com.santiihoyos.pocketguide.main.view

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.santiihoyos.base.feature.abstracts.BaseActivity
import com.santiihoyos.marvelguide.R
import com.santiihoyos.pocketguide.di.AppComponent
import com.santiihoyos.pocketguide.main.viewmodel.MainViewModel
import javax.inject.Inject

/**
 * App entry point
 */
class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    override lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    override fun inject() = AppComponent.instance.inject(this)

    override fun getViewModelAbstract(): Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigation()
    }

    /**
     * Setups bottom nav with functionality fragments
     */
    private fun setupBottomNavigation() {

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }
}
