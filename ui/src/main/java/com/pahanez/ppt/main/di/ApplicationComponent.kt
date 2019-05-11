package com.pahanez.ppt.main.di

import android.app.Application
import com.pahanez.ppt.glue.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
                ViewModelModule::class
    ]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: com.pahanez.ppt.main.PPTApp)


}