package com.dflorencia.movieapp.root

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dflorencia.movieapp.R
import com.dflorencia.movieapp.api.Movie
import com.dflorencia.movieapp.movie.ApiStatus
import com.dflorencia.movieapp.movie.MovieAdapter

@BindingAdapter("data")
fun setData(recyclerView: RecyclerView, data: List<Movie>?){
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
    val controller = AnimationUtils.loadLayoutAnimation(recyclerView.context, R.anim.recycler_view_animation)
    recyclerView.layoutAnimation = controller
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    url?.let {
        val baseUrl = imageView.context.getString(R.string.base_url_images_large)

        val absolutUrl = baseUrl + url

        Glide.with(imageView.context)
            .load(absolutUrl)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken))
            .into(imageView)

        imageView.scaleType = ImageView.ScaleType.FIT_XY
    } ?: run {
        imageView.setImageResource(R.drawable.ic_broken)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER;
    }
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