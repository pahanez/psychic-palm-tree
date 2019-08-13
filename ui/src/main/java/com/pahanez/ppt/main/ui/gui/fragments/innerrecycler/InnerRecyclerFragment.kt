package com.pahanez.ppt.main.ui.gui.fragments.innerrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pahanez.ppt.glue.base.BaseFragment
import com.pahanez.ppt.main.R
import kotlinx.android.synthetic.main.fragment_inner_recycler.*
import java.util.*

class InnerRecyclerFragment : BaseFragment() {

    companion object {
        fun newInstance() = InnerRecyclerFragment()
    }

    private val random = Random()

    override var layoutId = R.layout.fragment_inner_recycler

    override fun inject() {
        // Do nothing
    }

    override fun initView(view: View) {
        outerRecycler.layoutManager = LinearLayoutManager(activity)
        val adapter = OuterAdapter()
        outerRecycler.adapter = adapter

        adapter.swap(getOuterModels())
    }

    private fun getOuterModels(): List<OuterModel> {
        return mutableListOf<OuterModel>().let {
            for (count in 0 .. 100) {
                it.add(OuterModel(UUID.randomUUID().toString(), getInnerModels(count)))
            }
            it
        }
    }

    private fun getInnerModels(count: Int): List<InnerModel> {
        return mutableListOf<InnerModel>().let {
            for (count in 0 .. 100) {
                it.add(InnerModel(UUID.randomUUID().toString(), "https://placekitten.com/g/${200+random.nextInt(100)}/${300 + random.nextInt(100)}"))
            }
            it
        }
    }

    data class OuterModel(val id: String, val innerList: List<InnerModel>)

    data class InnerModel(val id: String, val imgUrl: String)


    inner class OuterAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private var items = emptyList<OuterModel>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return OuterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_item_outer, null))
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as OuterViewHolder).update(items[position].innerList)
        }

        fun swap(models: List<OuterModel>) {
            this.items = models
            notifyDataSetChanged()
        }

        inner class OuterViewHolder(view: View): RecyclerView.ViewHolder(view) {
            var adapter: InnerAdapter = InnerAdapter()

            init {
                val recycler = view.findViewById<RecyclerView>(R.id.innerRecycler)
                recycler.adapter = adapter
            }

            fun update(innerModels: List<InnerModel>) {
                adapter.swap(innerModels)
            }

        }

    }

    inner class InnerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private var items = emptyList<InnerModel>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return InnerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_item_inner, null))
        }

        override fun getItemCount(): Int = items.size


        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as InnerViewHolder).update(items[position])
        }

        fun swap(models: List<InnerModel>) {
            this.items = models
            notifyDataSetChanged()
        }

        inner class InnerViewHolder(view: View): RecyclerView.ViewHolder(view) {

            fun update(innerModel: InnerModel) {
                Glide.with(itemView.context)
                    .load(innerModel.imgUrl)
                    .into(itemView.findViewById<ImageView>(R.id.innerImage))
            }

        }

    }


}