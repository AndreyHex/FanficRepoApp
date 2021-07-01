package com.example.fanficrepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fanficrepo.databinding.FragmentChapterBinding
import com.example.fanficrepo.model.FanficListViewModel

class ChapterFragment : Fragment() {

    private var _binding: FragmentChapterBinding? = null

    private val binding get() = _binding!!

    private val fanficListViewModel: FanficListViewModel by activityViewModels()

    private var chapterNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            chapterNumber = it.getInt("chapterNumber")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChapterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var chapters = fanficListViewModel.chapterList.value
        if (chapters == null) {
            binding.chapterText.text = "not found"
            binding.chapterTitle.text = "not found"
            return
        }
        val chapter = chapters[chapterNumber-1]
        with (binding) {
            chapterTitle.text = chapter.title
            chapterText.text = chapter.text
        }
        (activity as MainActivity).supportActionBar?.title = chapter.title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
