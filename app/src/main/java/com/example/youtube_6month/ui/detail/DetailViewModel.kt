package com.example.youtube_6month.ui.detail

import androidx.lifecycle.LiveData
import com.example.youtube_6month.core.network.results.Resource
import com.example.youtube_6month.core.ui.BaseViewModel
import com.example.youtube_6month.data.remote.model.PlaylistItem
import com.example.youtube_6month.data.remote.model.Playlists
import com.example.youtube_6month.repository.Repository

class DetailViewModel (private val repository: Repository): BaseViewModel() {

    fun getPlayListItems(playlistId:String?): LiveData<Resource<PlaylistItem>> {
        return repository.getPlaylistItems(playlistId!!)
    }

}