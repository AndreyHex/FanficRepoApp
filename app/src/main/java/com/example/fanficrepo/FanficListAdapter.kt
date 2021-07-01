package com.example.fanficrepo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fanficrepo.databinding.FanficItemBinding
import com.example.fanficrepo.entity.Fanfic

class FanficListAdapter(val fanficList: List<Fanfic>?) : RecyclerView.Adapter<FanficListAdapter.FanficHolder>() {

    class FanficHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = FanficItemBinding.bind(view)
        fun bind(fanfic: Fanfic, position: Int) {
            binding.fanficTitleText.text = fanfic.title
            binding.fanficAuthorText.text = fanfic.author
            binding.fanficFandomText.text = fanfic.fandom
            binding.fanficDescriptionText.text = fanfic.description
            binding.fanficCard.setOnClickListener {
                this.itemView.findNavController().navigate(
                    FanficListFragmentDirections.actionFanficListFragmentToFanficFragment(fanficIndex = position)
                )
            }
            binding.tagList.layoutManager = LinearLayoutManager(this.itemView.context, RecyclerView.HORIZONTAL, false)
            binding.tagList.adapter = TagListAdapter(fanfic.tags)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FanficHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fanfic_item, parent, false)
        return FanficHolder(layout)
    }

    override fun onBindViewHolder(holder: FanficHolder, position: Int) {
        holder.bind(fanficList!![position], position)

    }

    override fun getItemCount(): Int {
        return fanficList?.size ?: 0
    }
}