package com.pahanez.ppt.main.ui.main

import com.pahanez.ppt.glue.di.BaseActivityComponent
import com.pahanez.ppt.glue.di.CoreComponent
import dagger.BindsInstance
import dagger.Component
import io.plaidapp.core.dagger.scope.FeatureScope


@FeatureScope
@Component(dependencies = [CoreComponent::class])
interface MainActivityComponent: BaseActivityComponent<MainActivity> {

    @Component.Builder
    interface Builder {
        fun build(): MainActivityComponent

        @BindsInstance
        fun homeActivity(activity: MainActivity): Builder
        fun coreComponent(module: CoreComponent): Builder
    }

}