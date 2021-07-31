package com.dflorencia.movieapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dflorencia.movieapp.api.Item
import com.dflorencia.movieapp.overview.ItemAdapter

@BindingAdapter("data")
fun setData(recyclerView: RecyclerView, data: List<Item>?){
    val adapter = recyclerView.adapter as ItemAdapter
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