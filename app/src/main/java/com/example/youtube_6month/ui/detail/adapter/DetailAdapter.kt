package com.example.youtube_6month.ui.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube_6month.core.utils.loadImage
import com.example.youtube_6month.data.remote.model.PlaylistItem
import com.example.youtube_6month.databinding.ItemDetailBinding

class DetailAdapter(private val onClick: (PlaylistItem.Item) -> Unit) :
    RecyclerView.Adapter<DetailAdapter.PlaylistItemViewHolder>() {
    private var playlistItem = listOf<PlaylistItem.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<PlaylistItem.Item?>?) {
        this.playlistItem = list as List<PlaylistItem.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistItemViewHolder {
        return PlaylistItemViewHolder(
            ItemDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistItemViewHolder, position: Int) {
        holder.bind(playlistItem[position])
    }

    override fun getItemCount(): Int {
        return playlistItem.size
    }

    inner class PlaylistItemViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlaylistItem.Item?) {
            with(binding) {
                item?.snippet?.thumbnails?.standard?.url?.let { binding.image.loadImage(it) }
                tvTitle.text = item?.snippet?.title
            }

            itemView.setOnClickListener {
                onClick.invoke(item!!)
            }
        }
    }
}