package com.pahanez.ppt.glue.di

import com.pahanez.ppt.glue.viewmodel.ViewModelModule
import dagger.Component

@Component(
    modules = [ViewModelModule::class]
)
interface GlueComponent {

}