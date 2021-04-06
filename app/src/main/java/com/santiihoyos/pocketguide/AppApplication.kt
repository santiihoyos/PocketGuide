package com.santiihoyos.pocketguide

import android.app.Application
import com.santiihoyos.pocketguide.di.AppComponent

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Injector initialization
        AppComponent.init(this)
    }
}
