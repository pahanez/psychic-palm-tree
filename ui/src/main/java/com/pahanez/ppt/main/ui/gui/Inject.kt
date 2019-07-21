package com.pahanez.ppt.main.ui.gui

import com.pahanez.ppt.main.coreComponent

/**
 * Injector for GuiActivity.
 */
fun inject(activity: GuiActivity): GuiActivityComponent {
    val compoenent = DaggerGuiActivityComponent.builder()
        .coreComponent(activity.coreComponent())
        .guiActivity(activity)
        .build()

        compoenent.inject(activity)

    return compoenent
}