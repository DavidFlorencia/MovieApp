package com.dflorencia.movieapp.movie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.dflorencia.movieapp.R
import com.dflorencia.movieapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment:Fragment() {

    private val apiKey = "AIzaSyDkaZDx7jzjPqYI6H5g1t9z2xfbwT43lSI"

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setMovie(args.movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.movie.observe(viewLifecycleOwner){
            (activity as AppCompatActivity).supportActionBar?.title = it.title
        }

        /* This listener should be load the URL of the corresponding movie trailer
         like viewMode.movie.url, but this task is more complex because TheMovieDataBase
         api doesn't include url to movie trailer.*/
        binding.imgPlayIcon.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEARCH)
            intent.setPackage("com.google.android.youtube")
            intent.putExtra("query", viewModel.movie.value?.title)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

/*            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/watch?v=X5otNZzKmZs"))*/

            startActivity(intent)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.detail_from_movie)
    }
}