package com.dflorencia.movieapp.root

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dflorencia.movieapp.R
import com.dflorencia.movieapp.api.Movie
import com.dflorencia.movieapp.movie.ApiStatus
import com.dflorencia.movieapp.movie.MovieAdapter

@BindingAdapter("data")
fun setData(recyclerView: RecyclerView, data: List<Movie>?){
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    val baseUrl = imageView.context.getString(R.string.base_url_images)
    val absolutUrl = baseUrl + url

    Glide.with(imageView.context)
        .load(absolutUrl)
        .into(imageView)
}

@BindingAdapter("apiStatus")
fun setStatus(statusImageView: ImageView,
               status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}