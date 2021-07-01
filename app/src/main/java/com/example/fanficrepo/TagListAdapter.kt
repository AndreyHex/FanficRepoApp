package com.example.fanficrepo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fanficrepo.databinding.TagItemBinding

class TagListAdapter(val tags: List<String>?) : RecyclerView.Adapter<TagListAdapter.TagHolder>() {

    class TagHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = TagItemBinding.bind(view)
        fun bind(tag: String?) {
            binding.tagText.text = tag ?: ""
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.tag_item, parent, false)
        return TagHolder(layout)
    }

    override fun onBindViewHolder(holder: TagHolder, position: Int) {
        holder.bind(tags?.get(position))
    }

    override fun getItemCount(): Int {
        return tags?.size ?: 0
    }

}
