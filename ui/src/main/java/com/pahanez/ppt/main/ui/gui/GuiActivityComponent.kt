package com.pahanez.ppt.main.ui.gui

import com.pahanez.ppt.glue.di.BaseActivityComponent
import com.pahanez.ppt.glue.di.CoreComponent
import dagger.BindsInstance
import dagger.Component
import io.plaidapp.core.dagger.scope.FeatureScope

@FeatureScope
@Component(dependencies = [CoreComponent::class])
interface GuiActivityComponent: BaseActivityComponent<GuiActivity> {

    @Component.Builder
    interface Builder {
        fun build(): GuiActivityComponent

        @BindsInstance
        fun guiActivity(activity: GuiActivity): Builder
        fun coreComponent(module: CoreComponent): Builder
    }

}