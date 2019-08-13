package com.pahanez.ppt.main.ui.gui.fragments.gles31

import android.view.View
import com.pahanez.ppt.glue.base.BaseFragment
import com.pahanez.ppt.main.R
import kotlinx.android.synthetic.main.fragment_gles31_demo.*

class Gles31DemoFragment : BaseFragment() {
    override fun inject() {
        // Do nothing
    }

    companion object {
        fun newInstance() = Gles31DemoFragment()
    }

    override var layoutId = R.layout.fragment_gles31_demo

    override fun initView(view: View) {
        b1.setOnClickListener {
            glSurfaceView.doActionOne()
        }
    }

    override fun onResume() {
        super.onResume()
        glSurfaceView.onResume()
    }

    override fun onPause() {
        super.onPause()
        glSurfaceView.onPause()
    }
}