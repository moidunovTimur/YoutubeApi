package com.example.youtube_6month.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.youtube_6month.databinding.ItemPlaylistsBinding
import com.example.youtube_6month.model.Item


class MainAdapter(private val onClick: (item: Item) -> Unit) :
    Adapter<MainAdapter.MainViewHolder>() {

    private var list = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemPlaylistsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MainViewHolder(private var binding: ItemPlaylistsBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.image.load(item.snippet.thumbnails.default.url)
            binding.tvTitle.text = item.snippet.title
            binding.tvVideo.text = "${item.contentDetails.itemCount} video"

            itemView.setOnClickListener {
                onClick.invoke(item)
            }

        }

    }
}