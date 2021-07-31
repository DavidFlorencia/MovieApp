package com.dflorencia.movieapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dflorencia.movieapp.api.Item
import com.dflorencia.movieapp.databinding.ItemViewBinding

class ItemAdapter(val clickListener: ItemClickListener): ListAdapter<Item,ItemAdapter.ItemViewHolder>(ItemDiffCallback()) {

    class ItemViewHolder private constructor(val binding:ItemViewBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item, clickListener: ItemClickListener){
            binding.item = item;
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

    class ItemDiffCallback: DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position) as Item
        holder.bind(item, clickListener)
    }

    class ItemClickListener(val clickListener: (item: Item) -> Unit){
        fun onClick(item: Item) = clickListener(item);
    }
}