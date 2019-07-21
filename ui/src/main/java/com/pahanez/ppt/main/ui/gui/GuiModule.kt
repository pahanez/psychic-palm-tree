package com.pahanez.ppt.main.ui.gui

import dagger.Module
import dagger.Provides
import io.plaidapp.core.dagger.scope.FeatureScope
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@Module
class GuiModule {

    @FeatureScope
    @Provides
    fun providesCicerone(): Cicerone<Router> {
        return Cicerone.create()
    }

    @FeatureScope
    @Provides
    fun providesRouter(cicerone: Cicerone<Router>): Router =
        cicerone.router

    @FeatureScope
    @Provides
    fun providesNavigationHolder(cicerone: Cicerone<Router>) = cicerone.navigatorHolder

}