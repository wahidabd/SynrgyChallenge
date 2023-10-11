package com.wahidabd.synrgy.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidabd.synrgy.R
import com.wahidabd.synrgy.databinding.FragmentHomeBinding
import com.wahidabd.synrgy.presentation.adapter.GenreAdapter
import com.wahidabd.synrgy.utils.GenreType
import com.wahidabd.synrgy.utils.JsonParser


class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private val genreAdapter by lazy { GenreAdapter() }
    private var isGrid = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(GenreType.LIST)
        initData()
        initListener()
    }

    private fun initListener() = with(binding) {
        cardType.setOnClickListener {
            isGrid = !isGrid
            handleTypeList(if (isGrid) GenreType.GRID else GenreType.LIST)
            initRecyclerView(if (isGrid) GenreType.GRID else GenreType.LIST)
        }
    }

    private fun initData() {
        genreAdapter.setList(JsonParser.getAllGenres(requireContext()))
    }

    private fun initRecyclerView(type: GenreType) {

        val manager = when (type) {
            GenreType.LIST -> LinearLayoutManager(requireContext())
            GenreType.GRID -> GridLayoutManager(requireContext(), 2)
        }

        binding.rvGenre.apply {
            adapter = genreAdapter
            layoutManager = manager
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun handleTypeList(type: GenreType) = with(binding) {
        genreAdapter.setOnGenreType(type)

        when (type) {
            GenreType.LIST -> imgType.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_widgets
                )
            )

            GenreType.GRID -> imgType.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_filter_list
                )
            )
        }
    }

}