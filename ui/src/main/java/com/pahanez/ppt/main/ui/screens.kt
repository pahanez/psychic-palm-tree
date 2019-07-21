package com.pahanez.ppt.main.ui

import androidx.fragment.app.Fragment
import com.pahanez.ppt.main.ui.gui.fragments.GuiLanding
import com.pahanez.ppt.main.ui.gui.fragments.gles31.Gles31DemoFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object GuiLanginScreen: SupportAppScreen() {
    override fun getFragment(): Fragment {
        return GuiLanding.newInstance()
    }
}

object Gles31DemoScreen: SupportAppScreen() {
    override fun getFragment(): Fragment {
        return Gles31DemoFragment.newInstance()
    }
}