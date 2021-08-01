package com.dflorencia.movieapp.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dflorencia.movieapp.api.Movie
import com.dflorencia.movieapp.databinding.FragmentOverviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment: Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.rvItems.adapter = MovieAdapter(MovieAdapter.MovieClickListener {
            navigateToMovieDetail(it)
        })

        return binding.root
    }

    override fun onResume() {
        (activity as AppCompatActivity).supportActionBar?.title = "Movies"
        super.onResume()
    }

    private fun navigateToMovieDetail(movie: Movie) {
        val direction = OverviewFragmentDirections.actionOverviewToDetail(movie)
        findNavController().navigate(direction)
    }
}