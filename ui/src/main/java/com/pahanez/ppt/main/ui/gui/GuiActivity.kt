package com.pahanez.ppt.main.ui.gui

import android.os.Bundle
import com.pahanez.ppt.glue.base.BaseActivity

class GuiActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(this)
    }



}