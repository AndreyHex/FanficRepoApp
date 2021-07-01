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
import com.example.fanficrepo.databinding.FragmentFanficListBinding
import com.example.fanficrepo.model.FanficListViewModel

class FanficListFragment : Fragment() {

    private var _binding: FragmentFanficListBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val fanficListViewModel: FanficListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFanficListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerFanficsView
        recyclerView.layoutManager = LinearLayoutManager(context)
        fanficListViewModel.fanficList.observe(viewLifecycleOwner, Observer{
            i -> recyclerView.adapter = FanficListAdapter(i)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}