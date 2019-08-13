package com.pahanez.ppt.main.ui.gui.fragments

import android.view.View
import android.widget.ArrayAdapter
import com.pahanez.ppt.glue.base.BaseFragment
import com.pahanez.ppt.main.R
import com.pahanez.ppt.main.ui.Gles31DemoScreen
import com.pahanez.ppt.main.ui.InnerRecyclerScreen
import com.pahanez.ppt.main.ui.gui.GuiActivity
import kotlinx.android.synthetic.main.fragment_gui_landing.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class GuiLanding : BaseFragment() {

    companion object {
        fun newInstance() = GuiLanding()
    }

    @Inject
    lateinit var router: Router

    override var layoutId: Int = R.layout.fragment_gui_landing

    val links = arrayOf("GLES31", "RECYCLER_IN_RECYCLER")

    override fun initView(view: View) {
        guiList.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, links)

        guiList.setOnItemClickListener { _, _, position, _->
            when (links[position]) {
                "GLES31" -> Gles31DemoScreen
                "RECYCLER_IN_RECYCLER" -> InnerRecyclerScreen
                else -> throw IllegalStateException("wrong screen type")
            }.also {
                router.navigateTo(it)
            }
        }
    }

    override fun inject() {
        (activity as GuiActivity).component.inject(this)
    }

}
