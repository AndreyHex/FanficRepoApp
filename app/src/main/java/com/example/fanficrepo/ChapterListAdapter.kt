package com.example.fanficrepo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fanficrepo.databinding.ChapterItemBinding
import com.example.fanficrepo.entity.Chapter
import com.example.fanficrepo.entity.Fanfic

class ChapterListAdapter(val fanfic: Fanfic) : RecyclerView.Adapter<ChapterListAdapter.ChapterHolder>() {

    class ChapterHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ChapterItemBinding.bind(view)
        fun bind(chapter: Chapter?) {
            if (chapter == null) return
            binding.chapterTitleText.text = chapter.title
            binding.chapterNumberText.text = "Chapter ${chapter.number.toString()}"
            binding.chapterItem.setOnClickListener {
                this.itemView.findNavController().navigate(
                    FanficFragmentDirections.actionFanficFragmentToChapterFragment(chapterNumber = chapter.number)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.chapter_item, parent, false)
        return ChapterHolder(layout)
    }

    override fun onBindViewHolder(holder: ChapterHolder, position: Int) {
        holder.bind(fanfic.chapters?.get(position))
    }

    override fun getItemCount(): Int {
        return fanfic.chapters?.size ?: 0
    }
}