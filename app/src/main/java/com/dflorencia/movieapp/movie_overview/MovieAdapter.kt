package com.dflorencia.movieapp.movie_overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dflorencia.movieapp.api.Movie
import com.dflorencia.movieapp.databinding.ItemViewBinding

class MovieAdapter(val clickListener: MovieClickListener): ListAdapter<Movie,MovieAdapter.ItemViewHolder>(ItemDiffCallback()) {

    class ItemViewHolder private constructor(val binding:ItemViewBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie, clickListener: MovieClickListener){
            binding.movie = movie;
            binding.clickListener = clickListener;
            binding.executePendingBindings();
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context);
                val binding = ItemViewBinding.inflate(layoutInflater, parent, false);
                return ItemViewHolder(binding);
            }
        }
    }

    class ItemDiffCallback: DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldMovie: Movie, newMovie: Movie): Boolean {
            return oldMovie === newMovie
        }

        override fun areContentsTheSame(oldMovie: Movie, newMovie: Movie): Boolean {
            return oldMovie.id == newMovie.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position) as Movie
        holder.bind(item, clickListener)
    }

    class MovieClickListener(val clickListener: (movie: Movie) -> Unit){
        fun onClick(movie: Movie) = clickListener(movie);
    }
}