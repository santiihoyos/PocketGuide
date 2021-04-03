package com.santiihoyos.marvelguide

import android.app.Application
import com.santiihoyos.marvelguide.di.AppComponent

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Injector initialization
        AppComponent.init(this)
    }
}
