package com.example.youtube_6month.ui.playlists.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.youtube_6month.data.remote.model.Item
import com.example.youtube_6month.databinding.ItemPlaylistsBinding


class MainAdapter(private val onClick: (Item) -> Unit) :
    Adapter<MainAdapter.MainViewHolder>() {

    private var listOfItems = arrayListOf<Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Item>){
        this.listOfItems = list as ArrayList<Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemPlaylistsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listOfItems[position])
    }

    override fun getItemCount(): Int {
        return listOfItems.size
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