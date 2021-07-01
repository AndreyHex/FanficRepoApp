package com.example.fanficrepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fanficrepo.databinding.FragmentFanficBinding
import com.example.fanficrepo.model.FanficListViewModel

class FanficFragment : Fragment() {

    private var _binding: FragmentFanficBinding? = null

    private val binding get() = _binding!!

    private var fanficIndex: Int = 0

    private val fanficListViewModel: FanficListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fanficIndex = it.getInt("fanficIndex")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFanficBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fanfic = fanficListViewModel.fanficList.value!![fanficIndex]
        with (binding) {
            recyclerChaptersView.layoutManager = LinearLayoutManager(context)
            recyclerChaptersView.adapter = ChapterListAdapter(fanfic)
            tagList2.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            tagList2.adapter = TagListAdapter(fanfic.tags)
            fanficTitle.text = fanfic.title
            fanficAuthor.text = fanfic.author
            fanficDescription.text = fanfic.description
        }
        (activity as MainActivity).supportActionBar?.title = fanfic.title
        fanficListViewModel.loadChapters(fanfic.id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}