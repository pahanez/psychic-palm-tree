package com.pahanez.ppt.main

import android.app.Activity
import android.app.Application
import android.content.Context
import com.pahanez.ppt.glue.di.CoreComponent
import com.pahanez.ppt.glue.di.DaggerCoreComponent

class PPTApp: Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.create()
    }

    override fun onCreate() {
        super.onCreate()
    }

    companion object {

        @JvmStatic
        fun coreComponent(context: Context): CoreComponent {
            return (context.applicationContext as PPTApp).coreComponent
        }
    }

}

fun Activity.coreComponent() = PPTApp.coreComponent(this)
