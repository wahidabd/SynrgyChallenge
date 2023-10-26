package com.wahidabd.synrgy.presentation.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wahidabd.synrgy.R
import com.wahidabd.synrgy.databinding.FragmentDetailMovieBinding
import com.wahidabd.synrgy.presentation.home.MovieViewModel
import com.wahidabd.synrgy.presentation.home.adapter.MovieAdapter


class DetailMovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModels()

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
        initProcess()
        initObservables()
    }

    private fun initProcess() {
        val json = requireContext().resources.openRawResource(R.raw.movies).bufferedReader().use { it.readText() }
        viewModel.getAllMovie(json, args.genre.id)
    }

    private fun initObservables() {
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            movieAdapter.setList(movies)
        }
    }

    private fun initView() = with(binding) {
        toolbar.title = args.genre.name
        rvMovie.adapter = movieAdapter
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