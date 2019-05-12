package com.pahanez.ppt.main.ui.main

import com.pahanez.ppt.main.coreComponent

/**
 * Injector for MainActivity.
 */
fun inject(activity: MainActivity) {
    DaggerMainActivityComponent.builder()
        .coreComponent(activity.coreComponent())
        .homeActivity(activity)
        .build()
        .inject(activity)
}