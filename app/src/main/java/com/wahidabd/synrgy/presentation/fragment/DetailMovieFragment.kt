package com.wahidabd.synrgy.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wahidabd.synrgy.databinding.FragmentDetailMovieBinding
import com.wahidabd.synrgy.presentation.adapter.MovieAdapter
import com.wahidabd.synrgy.utils.JsonParser


class DetailMovieFragment : Fragment() {

    private val movieAdapter by lazy { MovieAdapter() }
    private val args: DetailMovieFragmentArgs by navArgs()

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
        Log.d("Fragment", "This is a fragment")
    }

    private fun initView() = with(binding) {
        toolbar.title = args.genre.name
        rvMovie.adapter = movieAdapter

        val data = JsonParser.getListMovie(requireContext(), args.genre.id ?: 0)
        movieAdapter.setList(data)
    }

    private fun initListener() {
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        movieAdapter.setOnClickListener { movie ->
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/search?q=${movie.title}")
            )
            startActivity(intent)
        }
    }

}