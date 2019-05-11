package com.pahanez.ppt.main

import android.app.Application
import com.pahanez.ppt.glue.viewmodel.GlueModelFactory
import com.pahanez.ppt.main.di.ApplicationComponent
import com.pahanez.ppt.main.di.DaggerApplicationComponent
import com.pahanez.ppt.main.util.AppInjector
import javax.inject.Inject

class PPTApp: Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
    }

    @Inject
    lateinit var mvm: GlueModelFactory

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)

        appInstance = this


//        PPTApp.get().appComponent.inject(this)
//        println("mvm: $mvm")

    }

    companion object {

        private lateinit var appInstance: PPTApp

        fun get(): PPTApp {
            return appInstance
        }
    }

}