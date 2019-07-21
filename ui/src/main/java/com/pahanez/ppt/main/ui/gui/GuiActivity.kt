package com.pahanez.ppt.main.ui.gui

import android.os.Bundle
import com.pahanez.ppt.glue.base.BaseActivity
import com.pahanez.ppt.main.R
import com.pahanez.ppt.main.ui.GuiLanginScreen
import io.plaidapp.core.dagger.scope.FeatureScope
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class GuiActivity : BaseActivity() {

    @Inject
    @field:FeatureScope
    lateinit var navHolder: NavigatorHolder

    @Inject
    @field:FeatureScope
    lateinit var router: Router

    lateinit var component: GuiActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = inject(this)
        setContentView(R.layout.activity_gui)
        navHolder.setNavigator(SupportAppNavigator(this, supportFragmentManager, R.id.gui_container))
    }


    override fun onResume() {
        super.onResume()
        router.navigateTo(GuiLanginScreen)
    }


}